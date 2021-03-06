package com.spring.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hzb
 * @since 2019/09/30
 */
@Controller
@EnableScheduling
public class WebsocketMsgController {

    @Resource
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * index.html将message发送给后端，后端再将消息重组后发送到/topicTest/web-to-server-to-web
     *
     * @param message
     * @return
     * @throws Exception
     */
    @MessageMapping("/send")
    @SendTo("/topicTest/web-to-server-to-web")
    public String send(String message) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "服务器将原消息返回: " + df.format(new Date()) + " :" + message;
    }

    /**
     * 最基本的服务器端主动推送消息给前端
     *
     * @return
     * @throws Exception
     */
    @Scheduled(fixedRate = 1000)
    public String serverTime() throws Exception {
        // 发现消息
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        messagingTemplate.convertAndSend("/topicTest/servertime", df.format(new Date()));
        return "servertime";
    }

    /**
     * 以下面这种方式发送消息，前端订阅消息的方式为： stompClient.subscribe('/userTest/hzb/info'
     *
     * @return
     * @throws Exception
     */
    @Scheduled(fixedRate = 1000)
    public String serverTimeToUser() throws Exception {
        // 发现消息
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //这里虽然没有指定发送前缀为/userTest，但是在WebsocketConfig.java中设置了config.setUserDestinationPrefix("/userTest")，
        //否则默认为/user
        messagingTemplate.convertAndSendToUser("hzb", "/info", df.format(new Date()));
        return "serverTimeToUser";
    }
}

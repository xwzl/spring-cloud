package com.spring.netty.controller;

import com.spring.netty.client.NettyClient;
import com.spring.netty.protocol.protobuf.MessageBase;
import com.spring.netty.server.NettyServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author xuweizhi
 * @since 2019-09-22
 */
@RestController
public class ConsumerController {

    @Resource
    private NettyClient nettyClient;

    @Resource
    private NettyServer nettyServer;

    @GetMapping("/send")
    public String send(HttpServletRequest request) {
        MessageBase.Message message = new MessageBase.Message()
                .toBuilder().setCmd(MessageBase.Message.CommandType.NORMAL)
                .setContent("你好啊！")
                .setRequestId(UUID.randomUUID().toString()).build();
        nettyClient.sendMsg(message);
        nettyServer.sendMsg(message);
        return "send ok";
    }
}

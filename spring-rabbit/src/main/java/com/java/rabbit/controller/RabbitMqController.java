package com.java.rabbit.controller;

import com.java.rabbit.message.config.ProducerProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 返送消息队列
 *
 * @author xuweizhi
 * @date 2019/05/23 10:12
 */
@RestController
@RequestMapping("/rabbit")
public class RabbitMqController {

    private Logger log = LoggerFactory.getLogger(RabbitMqController.class);

    @Resource
    private AmqpTemplate amqpTemplate;

    @Resource
    private ProducerProcessor catProducer;

    @GetMapping("/death")
    public void testScheduledTask(int count) {
        for (int i = 1; i < count; i++) {
            final int qq = 2000 * i;
            String message1 = "这是定时任务测试!" + LocalDateTime.now().toString() + "          " + i;
            amqpTemplate.convertAndSend("deathExchange", "death", message1, message -> {
                message.getMessageProperties().setExpiration(qq + "");
                return message;
            });
        }
    }

    @GetMapping("/test1")
    public void test1() {
        catProducer.send("这是一个测试！");
    }

    /**
     * 生产者 1 ,按照匹配规则"topic.demo" 匹配队列 queue1,"topic.#" 匹配队列 queue2 ，两者皆能接收消息
     */
    @GetMapping("demo1")
    void demo1() {
        amqpTemplate.convertAndSend("topicExchange", "topic.demo", "这是第一个demo！");
    }

    /**
     * 生产者 2，按照匹配规则只有 queue2 能够消费信息
     */
    @GetMapping("demo2")
    void demo2() {
        amqpTemplate.convertAndSend("topicExchange", "topic.test", "这是第一个demo！");
    }


}

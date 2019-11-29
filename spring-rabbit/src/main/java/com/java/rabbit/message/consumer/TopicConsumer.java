package com.java.rabbit.message.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xuweizhi
 * @since 2019/07/14 22:47
 */
@Component
@Slf4j
public class TopicConsumer {

    @RabbitListener(queues = "queue1")
    @RabbitHandler
    public void queue1(String msg) {
        log.info("queue1" + msg);
    }

    @RabbitListener(queues = "queue2")
    @RabbitHandler
    public void queue2(String msg) {
        log.info("queue2" + msg);
    }

}

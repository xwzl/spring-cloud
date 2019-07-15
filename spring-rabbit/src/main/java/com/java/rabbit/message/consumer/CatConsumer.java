package com.java.rabbit.message.consumer;

import com.java.rabbit.message.config.DefaultMessageProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xuweizhi
 * @since 2019/07/14 13:03
 */
@Component
public class CatConsumer extends DefaultMessageProcessor {
    @RabbitListener(queues = "cat")
    @RabbitHandler
    @Override
    public void receive(String message) {
        System.out.println(message);
    }
}

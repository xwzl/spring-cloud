package com.java.rabbit.message.producer;

import com.java.rabbit.message.config.DefaultMessageProcessor;
import org.springframework.stereotype.Component;

/**
 * @author xuweizhi
 * @since 2019/07/14 12:58
 */
@Component
public class CatProducer extends DefaultMessageProcessor {

    @Override
    public void send(String message) {
        this.amqpTemplate.convertAndSend("animal", "cat", message);
    }
}

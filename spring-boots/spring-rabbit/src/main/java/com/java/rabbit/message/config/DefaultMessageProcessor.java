package com.java.rabbit.message.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xuweizhi
 * @since 2019/07/14 12:48
 */
@Component
public abstract class DefaultMessageProcessor implements ConsumerProcessor, ProducerProcessor {

    @Autowired
    protected AmqpTemplate amqpTemplate;

    @Override
    public void receive(String message) {

    }

    @Override
    public void send(String message) {

    }
}

package com.java.rabbit.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xuweizhi
 * @since 2019/07/12 19:52
 */
@Slf4j
@Component
public class DeathConsumer {

    @RabbitHandler
    @RabbitListener(queues = "consumerQueue")
    public void consumer(String msg) {
        log.info(msg);
    }

}

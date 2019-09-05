package com.java.rabbit.message.config;

/**
 * @author xuweizhi
 * @since 2019/07/14 12:47
 */
public interface ProducerProcessor {

    /**
     * 消息生产者
     */
    void send(String message);

}

package com.java.rabbit.message.config;

/**
 * @author xuweizhi
 * @since 2019/07/14 12:43
 */
public interface ConsumerProcessor {

    /**
     * 队列消费者顶级接口
     *
     * @param message 消息
     */
    void receive(String message);

}

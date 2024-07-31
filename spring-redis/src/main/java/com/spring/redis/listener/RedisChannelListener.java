package com.spring.redis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * Redis 订阅功能
 *
 * @author xuweizhi
 * @since 2022/03/10 22:40
 */
@Slf4j
public class RedisChannelListener implements MessageListener {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        Object value = stringRedisTemplate.getValueSerializer().deserialize(message.getBody());
        log.info("消费者{}", value);
    }
}

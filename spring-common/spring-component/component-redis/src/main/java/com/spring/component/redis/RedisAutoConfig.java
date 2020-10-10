package com.spring.component.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xuweizhi
 */
@Slf4j
@Configuration
@ConditionalOnClass(name = {"org.springframework.data.redis.core.RedisTemplate",
        "org.springframework.data.redis.connection.RedisConnectionFactory"})
public class RedisAutoConfig {

    @Bean
    public <T> JsonRedisTemplate<T> jsonRedisTemplate(RedisConnectionFactory connectionFactory) {
        log.debug("init JsonRedisTemplate");
        return new JsonRedisTemplate<>(connectionFactory);
    }

    @Bean
    public LongNumberRedisTemplate longNumberRedisTemplate(RedisConnectionFactory connectionFactory) {
        log.debug("init longNumberRedisTemplate");
        return new LongNumberRedisTemplate(connectionFactory);
    }

    // @Bean
    // public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
    // return new StringRedisTemplate(connectionFactory);
    // }
}

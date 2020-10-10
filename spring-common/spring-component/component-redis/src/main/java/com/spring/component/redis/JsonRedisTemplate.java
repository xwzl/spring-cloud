package com.spring.component.redis;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author xuweizhi
 */
public class JsonRedisTemplate<T> extends RedisTemplate<String, T> {

    public JsonRedisTemplate(RedisConnectionFactory connectionFactory) {
        setConnectionFactory(connectionFactory);
        setDefaultSerializer(RedisSerializer.json());
        setKeySerializer(RedisSerializer.string());
        setHashKeySerializer(RedisSerializer.string());
    }

}

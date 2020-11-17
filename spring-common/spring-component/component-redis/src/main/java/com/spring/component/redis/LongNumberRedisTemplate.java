package com.spring.component.redis;

import com.spring.component.redis.serializer.LongNumberSerializer;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

public class LongNumberRedisTemplate extends RedisTemplate<String, Long> {

    public LongNumberRedisTemplate(RedisConnectionFactory connectionFactory) {
        setConnectionFactory(connectionFactory);

        LongNumberSerializer longNumberSerializer = new LongNumberSerializer();
        setDefaultSerializer(longNumberSerializer);
        setKeySerializer(RedisSerializer.string());
        setHashKeySerializer(RedisSerializer.string());
    }

}

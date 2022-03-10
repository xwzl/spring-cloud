package com.spring.redis.config;

import com.spring.redis.listener.RedisChannelListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 * FactoryBean 配置
 *
 * @author xuweizhi
 * @since 2019/12/25 17:40
 */
@Configuration
public class BeanConfig {

    @Resource
    private LettuceConnectionFactory lettuceConnectionFactory;

    @Bean
    public CacheKeyGenerator cacheKeyGenerator() {
        return new LockKeyGenerator();
    }


    @SuppressWarnings("rawtypes")
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        return redisTemplate;
    }

    @Bean
    public RedisChannelListener redisChannelListener() {
        return new RedisChannelListener();
    }

    @Bean
    public ChannelTopic channelTopic() {
        return new ChannelTopic("string-topic");
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisChannelListener redisChannelListener, ChannelTopic channelTopic) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(lettuceConnectionFactory);
        container.addMessageListener(redisChannelListener, channelTopic);
        return container;
    }


}

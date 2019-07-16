package com.java.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author xuweizhi
 * @since 2019/07/15 17:14
 */
@Configuration
public class RedisConfig {

    /**
     * 设置RedisTemplate序列化器
     *
     * @param rcf 注入Redis工厂
     * @return 返回Redis模板
     */
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory rcf) {

        RedisTemplate<String, Object> rt = new RedisTemplate<>();
        // 配置连接工厂
        rt.setConnectionFactory(rcf);
        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = getObjectMapper();
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        // 设置value的序列化规则和 key的序列化规则
        rt.setValueSerializer(jackson2JsonRedisSerializer);
        // key采用String的序列化方式
        rt.setKeySerializer(new StringRedisSerializer());
        // hash的key也采用String的序列化方式
        rt.setHashKeySerializer(jackson2JsonRedisSerializer);
        // value序列化方式采用jackson
        rt.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        rt.setHashValueSerializer(jackson2JsonRedisSerializer);
        rt.setDefaultSerializer(jackson2JsonRedisSerializer);
        rt.setEnableDefaultSerializer(true);
        rt.afterPropertiesSet();
        return rt;
    }

    @Bean(name = "redisGenericTemplate")
    @SuppressWarnings({"rawtypes","unchecked"})
    public <T> RedisTemplate<String, T> redisGenericTemplate(RedisConnectionFactory rcf) {

        RedisTemplate<String, T> rt = new RedisTemplate<>();
        rt.setConnectionFactory(rcf);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        rt.setKeySerializer(new StringRedisSerializer());
        rt.setValueSerializer(jackson2JsonRedisSerializer);
        rt.afterPropertiesSet();
        return rt;
    }

    @Bean
    @SuppressWarnings({"rawtypes","unchecked"})
    public RedisTemplate<Object, Object> redisTemplateKeyObject(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> rt = new RedisTemplate<>();
        rt.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        rt.setKeySerializer(new StringRedisSerializer());
        rt.setValueSerializer(jackson2JsonRedisSerializer);
        rt.afterPropertiesSet();
        return rt;
    }

    @NotNull
    private ObjectMapper getObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 解决jackson2无法反序列化LocalDateTime的问题，新增的如果出错，删除
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        om.registerModule(new JavaTimeModule());
        return om;
    }
}

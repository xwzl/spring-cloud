package com.spring.demo.config.config;

import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Redis 官方提供 Redssion 分布式锁解决方案，有大神提出依然有缺陷
 *
 * @author xuweizhi
 * @since 2019/09/25 14:06
 */
public class RedSessionConfig {

    /**
     * 分布式锁客户端
     *
     * @param redissonClient ？
     * @return ？
     */
    @Bean
    CacheManager redSessionManager(RedissonClient redissonClient) {
        Map<String, CacheConfig> config = new HashMap<>();
        config.put("cache", new CacheConfig(1000 * 60, 1000 * 30));
        return new RedissonSpringCacheManager(redissonClient, config);
    }
}

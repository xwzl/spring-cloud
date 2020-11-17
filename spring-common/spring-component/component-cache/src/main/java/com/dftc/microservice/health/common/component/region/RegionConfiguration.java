package com.dftc.microservice.health.common.component.region;

import com.spring.component.json.JSON;
import com.spring.component.json.JsonAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.dftc.microservice.health.common.component.region.cache.MemoryCache;
import com.dftc.microservice.health.common.component.region.cache.RedisCache;

@Configuration
@AutoConfigureAfter({JsonAutoConfiguration.class})
public class RegionConfiguration {

    @Bean(name = "regionRedisCache")
    public RedisCache redisCache(StringRedisTemplate stringRedisTemplate,
        RegionConfigurationProperties regionConfigurationProperties, JSON json) {
        return new RedisCache(stringRedisTemplate, regionConfigurationProperties, json);
    }

    @Bean(name = "regionMemoryCache")
    public MemoryCache memoryCache(RegionConfigurationProperties regionConfigurationProperties, RedisCache redisCache) {
        return new MemoryCache(regionConfigurationProperties, redisCache);
    }

    @Bean(name = "region")
    public Region region(MemoryCache memoryCache) {
        return new Region(memoryCache);
    }
}

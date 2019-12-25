package com.spring.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FactoryBean 配置
 *
 * @author xuweizhi
 * @since 2019/12/25 17:40
 */
@Configuration
public class BeanConfig {

    @Bean
    public CacheKeyGenerator cacheKeyGenerator(){
        return new LockKeyGenerator();
    }
}

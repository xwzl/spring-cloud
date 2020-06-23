package com.java.prepare.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * 可以不用配置，官方文档
 * <p>
 * https://github.com/redisson/redisson/wiki
 *
 * @author xuweizhi
 * @since 2020/05/25 21:20
 */
//@Configuration
public class RedissonConfig {

    /**
     * 获取
     */
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.208.128:6379").setDatabase(0);
        return Redisson.create(config);
    }

}


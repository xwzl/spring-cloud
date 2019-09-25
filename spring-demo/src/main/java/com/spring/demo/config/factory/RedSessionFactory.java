package com.spring.demo.config.factory;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Redis 分布式锁
 *
 * @author xuweizhi
 * @since 2019/09/25 14:51
 */
@Component
public class RedSessionFactory {

    @Resource
    private RedisTemplate<String, Long> redisTemplate;

    @Resource
    private RedissonClient redissonClient;

    /**
     * 初始化为 0，CAS 操作
     *
     * @param key 键
     * @return 返回值
     */
    public long incrementAndGet(String key) {
        return new RedisAtomicLong(key, redisTemplate).incrementAndGet();
    }

    /**
     * 设定指定的初始化值
     *
     * @param key       键
     * @param initValue 值
     * @return 结果
     */
    public long incrementAndGetInitValue(String key, Long initValue) {
        return new RedisAtomicLong(key, redisTemplate, initValue).incrementAndGet();
    }

    /**
     * 加锁,这个加锁有问题，勿用
     *
     * @param key 键
     * @return 结果
     */
    public boolean tryLock(String key) {
        RLock rLock = redissonClient.getLock(key);
        return rLock.tryLock();
    }

    /**
     * 获取锁对象
     *
     * @param key 键
     * @return 结果
     */
    public RLock getLock(String key) {
        return redissonClient.getLock(key);
    }

    /**
     * 释放锁，释放锁代码有问题
     *
     * @param key 键
     */
    public void unlock(String key) {
        RLock rLock = redissonClient.getLock(key);
        rLock.unlock();
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return 结果
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

}

package com.spring.demo.untils;

import com.spring.demo.config.redis.DistributedLocker;
import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * @author xuweizhi
 * @since 2019/09/25 17:07
 */
public class RedissLockUtil {

    private static DistributedLocker redissLock;

    /**
     * 设置工具类使用的locker
     *
     * @param locker 锁对象
     */
    public static void setLocker(DistributedLocker locker) {
        redissLock = locker;
    }

    /**
     * 加锁
     *
     * @param key 锁
     */
    public static RLock lock(String key) {
        return redissLock.lock(key);
    }

    /**
     * 释放锁
     *
     * @param key 锁
     */
    public static void unlock(String key) {
        redissLock.unlock(key);
    }

    /**
     * 释放锁
     *
     * @param lock 锁
     */
    public static void unlock(RLock lock) {
        redissLock.unlock(lock);
    }

    /**
     * 获取锁，超时释放
     *
     * @param key     锁
     * @param timeout 超时时间   单位：秒
     */
    public static RLock lock(String key, int timeout) {
        return redissLock.lock(key, timeout);
    }

    /**
     * 获取锁，超时释放
     *
     * @param key 锁
     * @return 锁对象
     */
    public boolean trylock(String key) {
        return redissLock.tryLock(key);
    }

    /**
     * 获取锁，超时释放，指定时间单位
     *
     * @param key     锁
     * @param unit    时间单位
     * @param timeout 超时时间
     */
    public static RLock lock(String key, TimeUnit unit, int timeout) {
        return redissLock.lock(key, unit, timeout);
    }

    /**
     * 尝试获取锁，获取到立即返回true,获取失败立即返回false
     *
     * @param key       锁
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     */
    public static boolean tryLock(String key, int waitTime, int leaseTime) {
        return redissLock.tryLock(key, TimeUnit.SECONDS, waitTime, leaseTime);
    }

    /**
     * 尝试获取锁，在给定的waitTime时间内尝试，获取到返回true,获取失败返回false,获取到后再给定的leaseTime时间超时释放
     *
     * @param key       键
     * @param unit      单位
     * @param waitTime  等待时间
     * @param leaseTime 释放锁时间
     * @return 结果
     */
    public static boolean tryLock(String key, TimeUnit unit, long waitTime, long leaseTime) {
        return redissLock.tryLock(key, unit, waitTime, leaseTime);
    }

    /**
     * 锁释放被任意一个线程持有
     *
     * @param lockKey
     * @return
     */
    public static boolean isLocked(String lockKey) {
        return redissLock.isLocked(lockKey);
    }
}

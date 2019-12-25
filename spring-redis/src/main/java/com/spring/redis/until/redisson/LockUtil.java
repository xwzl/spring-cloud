package com.spring.redis.until.redisson;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁工具类
 *
 * @author xuweizhi
 * @since 2019/12/25 15:08
 */
public class LockUtil {

    private static Locker locker;

    /**
     * 设置工具类使用的locker
     *
     * @param locker 锁
     */
    public static void setLocker(Locker locker) {
        LockUtil.locker = locker;
    }

    /**
     * 获取锁
     *
     * @param lockKey key
     */
    public static void lock(String lockKey) {
        locker.lock(lockKey);
    }

    /**
     * 释放锁
     *
     * @param lockKey 锁
     */
    public static void unlock(String lockKey) {
        locker.unlock(lockKey);
    }

    /**
     * 获取锁，超时释放
     *
     * @param lockKey 锁
     * @param timeout 超时时间
     */
    public static void lock(String lockKey, int timeout) {
        locker.lock(lockKey, timeout);
    }

    /**
     * 获取锁，超时释放，指定时间单位
     *
     * @param lockKey key
     * @param unit    时间
     * @param timeout 单位
     */
    public static void lock(String lockKey, TimeUnit unit, int timeout) {
        locker.lock(lockKey, unit, timeout);
    }

    /**
     * 尝试获取锁，获取到立即返回true,获取失败立即返回false
     *
     * @param lockKey 锁
     * @return 是否获取锁
     */
    public static boolean tryLock(String lockKey) {
        return locker.tryLock(lockKey);
    }

    /**
     * 尝试获取锁，在给定的waitTime时间内尝试，获取到返回true,获取失败返回false,获取到后再给定的leaseTime时间超时释放
     *
     * @param lockKey   锁
     * @param waitTime  等待时间
     * @param leaseTime 超时时间
     * @param unit      时间单位
     * @return 结果
     * @throws InterruptedException 异常
     */
    public static boolean tryLock(String lockKey, long waitTime, long leaseTime,
                                  TimeUnit unit) throws InterruptedException {
        return locker.tryLock(lockKey, waitTime, leaseTime, unit);
    }

    /**
     * 锁释放被任意一个线程持有
     *
     * @param lockKey 释放锁
     * @return 返回值
     */
    public static boolean isLocked(String lockKey) {
        return locker.isLocked(lockKey);
    }
}

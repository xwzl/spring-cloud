package com.spring.demo.config.redis;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * redis 分布式锁顶级接口
 *
 * @author xuweizhi
 * @since 2019/09/25 16:53
 */
public interface DistributedLocker {


    /**
     * 获取锁，如果锁不可用，则当前线程处于休眠状态，直到获得锁为止。
     *
     * @param key 键
     * @return 锁对象
     */
    RLock lock(String key);

    /**
     * 获取锁,如果锁不可用，则当前线程处于休眠状态，直到获得锁为止。如果获取到锁后，执行结束后解锁或达到超时时间后会自动释放锁
     *
     * @param key     键
     * @param timeout 对象
     * @return 锁对象
     */
    RLock lock(String key, int timeout);

    /**
     * 获取锁,如果锁不可用，则当前线程处于休眠状态，直到获得锁为止。如果获取到锁后，执行结束后解锁或达到超时时间后会自动释放锁
     *
     * @param key     键
     * @param unit    单位
     * @param timeout 过期时间
     * @return 锁对象
     */
    RLock lock(String key, TimeUnit unit, int timeout);

    /**
     * 尝试获取锁，获取到立即返回true,未获取到立即返回false
     *
     * @param key 键
     * @return 结果
     */
    boolean tryLock(String key);

    /**
     * 尝试获取锁，获取到立即返回true,未获取到立即返回false
     *
     * @param key       键
     * @param unit      单位
     * @param waitTime  等待加锁时间
     * @param leaseTime ？
     * @return 锁对象
     */
    boolean tryLock(String key, TimeUnit unit, long waitTime, long leaseTime);

    /**
     * key 值释放锁
     *
     * @param key 键
     */
    void unlock(String key);

    /**
     * 锁对象释放锁
     *
     * @param lock 锁对象
     */
    void unlock(RLock lock);

    /**
     * 锁是否被任意一个线程锁持有
     *
     * @param key 键
     * @return 状态
     */
    boolean isLocked(String key);
}

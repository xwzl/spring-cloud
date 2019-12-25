package com.spring.redis.until.know;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * Redis 锁顶级接口
 *
 * @author xuweizhi
 * @since 2019/12/25 15:05
 */
public interface DistributedLocker {

    RLock lock(String lockKey);

    RLock lock(String lockKey, int timeout);

    RLock lock(String lockKey, TimeUnit unit, int timeout);

    boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime);

    void unlock(String lockKey);

    void unlock(RLock lock);
}

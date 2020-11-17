package com.spring.component.redis.lock;

import java.util.concurrent.TimeUnit;

public interface Lock {

    boolean tryLock();

    boolean tryLock(long time, TimeUnit unit) throws InterruptedException;

    boolean unlock();
}

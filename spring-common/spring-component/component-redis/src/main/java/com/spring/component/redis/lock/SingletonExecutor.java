package com.spring.component.redis.lock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SingletonExecutor {

    private SingletonExecutor() {}

    public static boolean execute(Lock lock, Runnable runnable, boolean unlock) {
        if (!lock.tryLock()) {
            return false;
        }
        try {
            runnable.run();
            return true;
        } finally {
            if (unlock) {
                lock.unlock();
            }
        }
    }
}

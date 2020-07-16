package com.java.prepare.until.java.validate;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 可重入锁
 *
 * @author xuweizhi
 * @since 2020/07/16 16:21
 */
public class ReloadLock {

    public static void main(String[] args) {
        Baby baby = new Baby();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 10, 0, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10),
                new ThreadFactoryBuilder().setNameFormat("hello %d").build());
        for (int i = 0; i < 10; i++) {
            poolExecutor.execute(baby::print1);
        }
        while (poolExecutor.getActiveCount() > 0) {
        }
    }

}

@Slf4j
class Baby {

    public synchronized void print1() {
        log.info("print1");
        print2();
    }

    public synchronized void print2() {
        log.info("print2");
    }

}

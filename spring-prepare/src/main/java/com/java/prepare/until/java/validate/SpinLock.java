package com.java.prepare.until.java.validate;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuweizhi
 * @since 2020/07/16 16:47
 */
@Slf4j
public class SpinLock {

    ReentrantLock lock = new ReentrantLock();

    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();

    volatile int mark = 1;

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        spinLock.a();
        spinLock.a();
        spinLock.c();
        spinLock.c();
        spinLock.b();
        spinLock.b();
    }

    public void a() {
        new Thread(() -> {
            try {
                while (true) {
                    lock.lock();
                    while (mark != 1) {
                        c1.await();
                    }
                    Thread.sleep(100);
                    log.info("A");
                    mark = 2;
                    c2.signalAll();
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void b() {
        new Thread(() -> {
            try {
                while (true) {
                    lock.lock();
                    while (mark != 2) {
                        c2.await();
                    }
                    Thread.sleep(100);
                    log.info("B");
                    mark = 3;
                    c3.signalAll();
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void c() {
        new Thread(() -> {
            try {
                while (true) {
                    lock.lock();
                    while (mark != 3) {
                        c3.await();
                    }
                    Thread.sleep(100);
                    log.info("C");
                    mark = 1;
                    c1.signalAll();
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

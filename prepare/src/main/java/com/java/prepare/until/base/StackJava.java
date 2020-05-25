package com.java.prepare.until.base;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 栈的 Java 实现
 *
 * @author xuweizhi
 * @since 2020/05/18 22:43
 */
@Slf4j
public class StackJava<T> {

    private int top = -1;

    private T[] elements;

    private int size = 10;

    public StackJava() {
        elements = (T[]) new Object[size];
    }

    public int push(T t) {
        if (top >= size - 1) {
            size = (int)
                    (size * 1.5);
            T[] news = (T[]) new Object[size];
            System.arraycopy(elements, 0, news, 0, elements.length);
            elements = news;

        }
        top++;
        elements[top] = t;
        return top;
    }

    public T pop() {
        if (top <= -1) {
            System.out.println("出错了");
        }
        return elements[top--];
    }

    public int size() {
        return top + 1;
    }

    /**
     * 二进制转换为 10 禁止
     */
    @Test
    public void test() {
        StackJava<Integer> stack = new StackJava<>();
        for (int i = 0; i < 8; i++) {
            int t = Math.random() > 0.5 ? 1 : 0;
            log.info("{}", t);
            stack.push(t);
        }
        int sum = 0;
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            sum = sum + stack.pop()* (1 << i);
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        StackJava<Integer> s = new StackJava<>();
        //for (int i = 0; i < 100; i++) {
        //    s.push(i);
        //}
        //for (int i = 0; i < 100; i++) {
        //    System.out.println(s.pop());
        //}
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS,
                new SynchronousQueue<>(), new ThreadFactoryBuilder().setNamePrefix("Thread ").build());
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(() -> {
                while (true) {
                    //synchronized (s) {
                    lock.lock();
                    int x = (int) (Math.random() * 10);
                    s.push(x);
                    if (Math.random() > 0.3) {
                        condition.signalAll();
                        log.info("唤醒线程{}", x);
                    } else {
                        log.info("被唤醒{}", x);
                    }
                    try {
                        //lock.wait();
                        log.info("等待状态");
                        condition.await();
                        log.info("等待状态后");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        log.info("睡眠");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("{}:{}", s.pop(), x);
                    lock.unlock();
                    //}
                }
            });
        }
    }
}

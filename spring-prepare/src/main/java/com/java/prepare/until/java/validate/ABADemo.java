package com.java.prepare.until.java.validate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA 问题
 *
 * @author xuweizhi
 * @since 2020/07/08 0:19
 */
public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(1);
    static AtomicStampedReference<Integer> stamp = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {
        System.out.println("--------------------以下是ABA问题的产生 ---------------------");
        new Thread(()->{
            atomicReference.compareAndExchange(1, 10);
            atomicReference.compareAndExchange(10, 1);
        },"t1").start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndExchange(1, 101)+":"+atomicReference.get());
        },"t2").start();

        System.out.println("--------------------以下是ABA问题的解决方案 ---------------------");
        new Thread(()->{
            int stamps = stamp.getStamp();
            System.out.println(Thread.currentThread().getName()+" 第1次版本号："+stamps);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stamp.compareAndSet(100, 101,stamp.getStamp(),stamp.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+" 第2次版本号："+stamp.getStamp());
            stamp.compareAndSet(101, 100,stamp.getStamp(),stamp.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+" 第3次版本号："+stamp.getStamp());
        },"t3").start();
        new Thread(()->{
            int stamps = stamp.getStamp();
            System.out.println(Thread.currentThread().getName()+" 第1次版本号："+stamps);
            try {
                // 暂定 3 秒钟，保证 t3 线程完成一次 ABA 操作
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stamp.compareAndSet(100, 1000,stamp.getStamp(),stamp.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+" 第2次版本号："+stamp.getStamp()+":"+stamp.getReference());
        },"t4").start();
    }

}

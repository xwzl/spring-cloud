package com.java.prepare.until.java.validate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 指令出重排序引起的的双重检测锁单例模式是失效
 *
 * @author xuweizhi
 * @since 2020/07/07 22:50
 */
public class DoubleLock {

    // 禁止指令重排序
    public static volatile DoubleLock instance;

    public DoubleLock() {
        System.out.println("对象初始化");
    }

    /**
     * 指令排序有可能会交换 2 和 3 之间的位置，因此给出去的引用有可能未完成舒适化
     * memory=allocate(); //1.分配对象内存空间
     * instance(memory);  //2.初始化对象
     * instance = memory; //3.设置 instance 指向刚分配的内存地址，此时 instance !=null
     */
    public static DoubleLock getInstance() {
        if (instance == null) {
            synchronized (DoubleLock.class) {
                if (instance == null)
                    instance = new DoubleLock();
            }
        }
        return instance;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Thread(DoubleLock::getInstance));
        }

        for (Thread thread : list) {
            thread.start();
        }

        AtomicInteger a = new AtomicInteger();
        a.getAndIncrement();
    }
}

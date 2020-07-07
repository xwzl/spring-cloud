package com.java.prepare.until.java.validate;

import com.java.prepare.model.Student;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子引用引起 ABA 问题，造成重复重置过程
 *
 * @author xuweizhi
 * @since 2020/07/07 22:50
 */
public class AtomicReferenceDemo {

    private static AtomicReference<Integer> money = new AtomicReference<Integer>();

    public static void demo(){
        AtomicReference<Student> atomicReference = new AtomicReference<>();

        Student student1 = new Student();
        student1.setAge(10);
        Student student2= new Student();
        student2.setAge(100);
        atomicReference.set(student1);
        System.out.println(atomicReference.compareAndSet(student1, student2)+":"+atomicReference.get().toString());
    }

    public static void main(String[] args) {
        //demo();
        buyTickets();
    }

    private static void buyTickets() {
        money.set(19);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, new SynchronousQueue<>(), (ThreadFactory) Thread::new);

        //模拟多个线程同时更新后台数据库，为用户充值
        for (int i = 0; i < 3; i++) {
            executor.execute(() -> {
                while (true) {
                    while (true) {
                        Integer m = money.get();
                        if (m < 20) {
                            if (money.compareAndSet(m, m + 20)) {
                                System.out.println("余额小于20元，充值成功，余额:" + money.get() + "元");
                                break;
                            }
                        } else {
                            //System.out.println("余额大于20元，无需充值");
                            break;
                        }
                    }
                }
            });
        }

        //用户消费线程，模拟消费行为
        executor.execute(() -> {
            for (int i = 0; i < 100; i++) {
                while (true) {
                    Integer m = money.get();
                    if (m > 10) {
                        System.out.println("大于10元");
                        if (money.compareAndSet(m, m - 10)) {
                            System.out.println("成功消费10元，余额:" + money.get());
                            break;
                        }
                    } else {
                        System.out.println("没有足够的金额");
                        break;
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        });
    }

}

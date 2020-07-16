package com.java.prepare.until.java.validate;

import org.jetbrains.annotations.Contract;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环栏栅：CyclicBarrier 是另外一种多线程控制工具。 和 CountDownLatch 非常类似，它可以实现线程间的计数等待，
 *
 * @author xuweizhi
 * @since 2019/09/02 13:37
 */
public class CyclicBarrierDemo {

    /**
     * 专门用于栏栅计数
     */
    public static class Soldier implements Runnable {

        private String soldier;

        /**
         * 这个对象用来对线程计数
         */
        private final CyclicBarrier cyclic;

        @Contract(pure = true)
        Soldier(CyclicBarrier cyclic, String soldierName) {
            this.cyclic = cyclic;
            this.soldier = soldierName;
        }

        @Override
        public void run() {
            try {
                // 等待所有士兵到齐，等待所有的线程创建好，会触发一次 Run 方法
                cyclic.await();
                doWork();
                //等待所有士兵完成工作，等待所有的线程执行完毕,会触发一次 Run 方法
                cyclic.await();

            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        void doWork() {
            try {
                Thread.sleep(Math.abs(new Random().nextInt() % 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soldier + ":任务完成");
        }
    }

    /**
     * 真正的执行那个啥呢？对，就是线程的 run 方法
     */
    public static class BarrierRun implements Runnable {
        boolean flag;
        int n;

        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            this.n = n;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("司令:[士兵" + n + "个，任务完成！]");
            } else {
                System.out.println("司令:[士兵" + n + "个，集合完毕！]");
                flag = true;
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {
        final int n = 10;
        Thread[] allSoldier = new Thread[n];
        boolean flag = false;
        CyclicBarrier cyclic = new CyclicBarrier(n, new BarrierRun(flag, n));
        //设置屏障点，主要是为了执行这个方法
        System.out.println("集合队伍！");
        for (int i = 0; i < n; ++i) {
            System.out.println("士兵 " + i + " 报道！");
            allSoldier[i] = new Thread(new Soldier(cyclic, "士兵 " + i));
            // 每一个新线程启动，CyclicBarrier 计数就会加一次，直到达到预计的数量，将重新计时
            allSoldier[i].start();
        }
    }
}

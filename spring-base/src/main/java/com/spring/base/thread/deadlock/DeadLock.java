package com.spring.base.thread.deadlock;


/**
 * jps 查看 java 进程
 * <p>
 * jstack 查看 java 进程中的详细信息
 *
 * @author xuweizhi
 */
public class DeadLock extends Thread {

    private Object tool;

    private static final Object FORK1 = new Object();

    private static final Object FORK2 = new Object();

    private DeadLock(Object obj) {
        this.tool = obj;
        if (tool == FORK1) {
            this.setName("哲学家A");
        }
        if (tool == FORK2) {
            this.setName("哲学家B");
        }
    }

    @Override
    public void run() {
        if (tool == FORK1) {
            synchronized (FORK1) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (FORK2) {
                    System.out.println("哲学家A开始吃饭了");
                }
            }

        }
        if (tool == FORK2) {
            synchronized (FORK2) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (FORK1) {
                    System.out.println("哲学家B开始吃饭了");
                }
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadLock 哲学家A = new DeadLock(FORK1);
        DeadLock 哲学家B = new DeadLock(FORK2);
        哲学家A.start();
        哲学家B.start();
        Thread.sleep(1000);
    }
}

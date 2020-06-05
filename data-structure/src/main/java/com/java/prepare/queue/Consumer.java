package com.java.prepare.queue;

/**
 * 消费者
 *
 * @author xuweizhi
 * @since 2020/05/27 23:42
 */
public class Consumer implements Runnable {
    WindowQueue queue;

    public Consumer(WindowQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (queue.isAlive) {
            try {
                queue.consumer();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


}

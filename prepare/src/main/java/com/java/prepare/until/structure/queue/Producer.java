package com.java.prepare.until.structure.queue;

/**
 * @author xuweizhi
 * @since 2020/05/27 23:42
 */
public class Producer  implements Runnable {

    WindowQueue queue;

    public Producer(WindowQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (queue.num < 100) {
            try {
                queue.producer();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}

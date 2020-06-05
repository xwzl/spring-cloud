package com.java.prepare.queue;

/**
 * 使用顺序循环队列和多线程实现一个排队买票的例子。而且，我们只允许这个队伍中同时排队的只有10个人，那就需要用到队列了。
 * 　　生产者（等候买票）
 * 　　消费者 (买票离开)
 *
 * @author xuweizhi
 * @since 2020/05/27 23:41
 */
public class WindowQueue {
    //卖票的队列
    int maxSize = 10;
    CircleSequenceQueue queue = new CircleSequenceQueue(maxSize);
    int num = 0; //统计卖票的数量，一天最多卖100张票。
    boolean isAlive = true; //判断是否继续卖票。

    //排队买票
    public synchronized void producer() throws Exception {
        if (queue.count < maxSize) {
            queue.append(num++); //等待买票的数量加1
            System.out.println("第" + num + "个客户排队等待买票！");
            this.notifyAll();//唤醒卖票的线程
        } else {
            try {
                System.out.println("队列已满...请等待！");
                this.wait();//队列满时，排队买票线程等待。
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    //卖票
    public synchronized void consumer() throws Exception {
        if (queue.count > 0) {
            Object obj = queue.delete();
            int temp = Integer.parseInt(obj.toString());
            System.out.println("第" + (temp + 1) + "个客户买到票离开队列！");
            //如果当前队列为空，并且卖出票的数量大于等于100，说明卖票结束
            if (queue.isEmpty() && this.num >= 100) {
                this.isAlive = false;
            }
            this.notifyAll(); //唤醒排队买票的线程。
        } else {
            try {
                System.out.println("队列已空...请等待！");
                this.wait();//队列空时，卖票线程等待。
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        WindowQueue queue = new WindowQueue();

        Producer p = new Producer(queue);//注意一定要传同一个窗口对象
        Consumer c = new Consumer(queue);

        //排队买票线程
        Thread pThread = new Thread(p);
        //卖票线程
        Thread cThread = new Thread(c);

        pThread.start(); //开始排队买票
        cThread.start();  //开始卖票
    }
}

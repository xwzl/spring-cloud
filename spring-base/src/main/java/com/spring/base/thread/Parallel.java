package com.spring.base.thread;

import com.spring.base.thread.pool.ThreadPoolUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <h1>并行流算法</h1>
 * <p>
 * 并发算法虽然可以充分发挥多核CPU的性能。但不幸的是，并非所有的计算都可以改造成并发的形式。那什么样的算法是无法使用并发进行计算的呢？简单来说，执行过程中有
 * 数据相关性的运算都是无法完美并行化的。
 * <p>
 * 假如现在有两个数，B和C，计算（B+C)×B/2,这个运行过程就是无法并行的。原因是，如果B+C没有执行完成，则永远筧不出（B+C)×B，这就是数据相关性·如果线程执行时所
 * 需的数据存在这种依赖关系，那么就没有办法将它们完美的并行化。
 * <p>
 * 遇到这种情况时，有没有什么补救措施呢？答案是肯定的，那就是借鉴日常生产中的流水线思想。
 * <p>
 * 比如，现在要生产一批小玩偶·小玩偶的制作分为四个步骤：第一，组装身体；第二在身体上安装四肢和头部；第三，给组装完成的玩偶穿上一件漂亮的衣服；第四，包装出货。
 * 为了加快制作进度，我们不可能叫四个人同时加工一个玩具，因为这四个步骤有着严重的依赖关系。如果没有身体，就没有地方安装四肢；如果没有组装完成，就不能衣服；如
 * 果没有穿上衣服，就不能包装发货。因此，找四个人来做一个玩偶是毫无意义的。
 * <p>
 * 但是，如果你现在要制作的不是1个玩偶，而是I万个玩偶，那情况就不同了。你可以找四个人，第一个人只负责组装身体，完成后交给第二个人；第二个人只负责安装头部和
 * 匹肢，完成后交付第三人：第三人只负责穿衣服，完成后交付第四人：第四人只负责包装发货。这样所有人都可以一起工作，共同完成任务，而整个时间周期也能缩短到原来
 * 的1/4左右，这就是流水线的思想。一旦流水线满载，每次只需要一步（假设一个玩偶需要四步）就可以产生一个玩偶。
 *
 * @author xuweizhi
 * @since 2019/09/23 16:08
 */
public class Parallel {
    /**
     * 为了实现这个功能，我们需要一个线程间携带结果进行信息交换的载体
     */
    static class Msg {
        double i;
        double j;
        String orgStr = null;
    }

    /**
     * 利用 LinkedBlockingQueue 的阻塞进行消息的传递
     */
    static class Multiply implements Runnable {
        static BlockingQueue<Msg> bq = new LinkedBlockingQueue<Msg>();

        @Override
        public void run() {
            while (true) {
                try {
                    Msg msg = bq.take();
                    msg.i = msg.i * msg.j;
                    Div.bq.add(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Div implements Runnable {
        static BlockingQueue<Msg> bq = new LinkedBlockingQueue<Msg>();

        @Override
        public void run() {
            while (true) {
                try {
                    Msg msg = bq.take();
                    msg.i = msg.i / 2;
                    System.out.println(msg.orgStr + "=" + msg.i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Plus implements Runnable {
        static BlockingQueue<Msg> bq = new LinkedBlockingQueue<Msg>();

        @Override
        public void run() {
            while (true) {
                try {
                    Msg msg = bq.take();
                    msg.j = msg.i + msg.j;
                    Multiply.bq.add(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = ThreadPoolUtils.noQueue();
        executor.execute(new Plus());
        executor.execute(new Multiply());
        executor.execute(new Div());

        for (int i = 1; i <= 1000; i++) {
            for (int j = 1; j <= 1000; j++) {
                Msg msg = new Msg();
                msg.i = i;
                msg.j = j;
                msg.orgStr = "((" + i + "+" + j + ")*" + i + ")/2";
                Plus.bq.add(msg);
            }
        }
        executor.shutdownNow();
    }
}



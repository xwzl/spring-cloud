package com.java.prepare.until.juc;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * https://blog.csdn.net/zycxnanwang/article/details/105563351
 *
 * @author xuweizhi
 * @since 2020/05/08 10:25
 */
@Slf4j
public class ThreadPooleReview {


    /**
     * AbortPolicy: 直接抛弃出异常 DiscardPolicy: 抛弃新的任务 DiscardOldestPolicy: 抛弃最老的任务 CallerRunsPolicy：让主线程来执行
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        threadExecuteTime();
    }

    /**
     * Future.get() 方法，按照顺序执行；
     *
     * Daemon的作用是为其他线程的运行提供服务，比如说GC线程。其实User Thread线程和Daemon Thread守护线程本质上来说去没啥区别的，唯一的区
     * 别之处就在虚拟机的离开时候：如果UserThread全部撤离，那么Daemon Thread也就没啥线程好服务的了，所以虚拟机也就退出了。只要当前JVM实例
     * 中尚存在任何一个非守护线程没有结束，守护线程就全部工作；只有当最后一个非守护线程结束时，守护线程随着JVM一同结束工作
     */
    private static void threadExecuteTime() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 0, TimeUnit.MINUTES, new LinkedBlockingQueue<>(10),
                new ThreadFactoryBuilder().setNameFormat("FutureTask-%d").setDaemon(true).build(),
            new ThreadPoolExecutor.CallerRunsPolicy());
        List<Future<String>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            long l = (long)(10000 * Math.random());
            log.info("" + l);
            Future<String> submit = executor.submit(() -> {
                Thread.sleep(l);
                return "" + l;
            });
            list.add(submit);
        }
        list.forEach(r -> {
            try {
                String s = r.get();
                log.info(new Date() + s);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 线程池判断核心线程需要用到的代码
     */
    @Test
    public void displacement() {
        int size = 32;
        int count = size - 3;
        int initIndex = (1 << count) - 1;
        int currentValue = initIndex;
        // 线程池计数初始值
        int atomicIndex = -1 << count;
        // 自动计算
        int threadAtomicIndex = atomicIndex | 0;
        for (int i = 0; i < 100; i++) {
            currentValue += +1;
            log.info(String.valueOf(currentValue));
            log.info(String.valueOf(initIndex & currentValue));
        }
    }

    @Test
    public void loopLabel() {
        outer:
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.println(i + ":" + j);
                if (j == 2) {
                    continue outer ;
                }
            }
        }
    }
}

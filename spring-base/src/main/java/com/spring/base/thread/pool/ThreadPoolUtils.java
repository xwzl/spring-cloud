package com.spring.base.thread.pool;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;

/**
 * 线程池工具，使用线程池要对线程进行异常捕捉，代码最后手动中断线程 Thread.currentThread().interrupt()
 *
 * @author xuweizhi
 * @since 2019/09/02 15:28
 */
@Slf4j
public class ThreadPoolUtils {

    /**
     * 当前机器 CPU 数量
     */
    private final static int CPU_NUMBERS = Runtime.getRuntime().availableProcessors();

    /**
     * 有界队列线程池
     * <p>
     * 当前线程数小于核心线程数，则创建新的线程；(n <= 20 )
     * 当前线程数大于核心线程数，队列未满，新加入的线程放入有界队列进行等待( 20 < n <= 40)
     * 当前线程数大于核心线程数，队列已满，小于最大线程数，进加入的线程直接创建线程执行任务( 40 < n <= 60)
     * 当前线程数大于最大线程数，执行默认拒绝策略，抛出异常。( n > 60)
     */
    @NotNull
    @Contract(" -> new")
    public static ThreadPoolExecutor boundedQueue() {
        return new ThreadPoolExecutor(CPU_NUMBERS, CPU_NUMBERS * 2, 60, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(CPU_NUMBERS), new ThreadFactory() {
            /**
             * 线程池新建线程调用这个方法
             */
            @Override
            public Thread newThread(@NotNull Runnable r) {
                log.info("新建线程成功");
                return new Thread(r);
            }
        });
    }

    /**
     * 无界队列
     * <p>
     * 由于无界队列的存在，最大线程数将失效，大于核心线程数的任务将直接进入无界队列，若线程的处理速度与任务创建速度相差过大，无界队列会保持快速增长，直到耗尽系统内存。
     */
    @NotNull
    @Contract(" -> new")
    public static ThreadPoolExecutor unboundedQueue() {
        return new ThreadPoolExecutor(CPU_NUMBERS, CPU_NUMBERS * 2, 60, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), (ThreadFactory) Thread::new);
    }

    /**
     * 无缓冲线程池：队列缓存将失效
     * <p>
     * 当前线程数没有达到最大线程数，有新任务加入时直接创建线程执行任务。当前线程数大于最大线程数，执行拒绝策略
     */
    @NotNull
    @Contract(" -> new")
    public static ThreadPoolExecutor noQueue() {
        return new ThreadPoolExecutor(CPU_NUMBERS, CPU_NUMBERS * 2, 60, TimeUnit.MILLISECONDS, new SynchronousQueue<>(), (ThreadFactory) Thread::new);
    }

    /**
     * 线程池拒绝策略
     * <p>
     * AbortPolicy: 该策略直接抛出异常，阻止系统正常工作。
     * CallerRunsPolicy 策略：只要线程池未关闭，该策略直接在调用者线程中，运行当前被丢弃的任务。显然这样做不会真的丢弃任务，但是，任务提价线程的性能极可能会急剧下降。
     * DiscardOldestPolicy 策略：该策略将丢弃最老的一个请求，也就是即将被执行的一个任务，并尝试再次提交当前任务。
     * DiscardPolicy 策略：该策略默默地丢弃无法处理的任务，不予任何处理。
     */
    @NotNull
    @Contract(" -> new")
    public static ThreadPoolExecutor rejectHandler() {
        return new ThreadPoolExecutor(CPU_NUMBERS, CPU_NUMBERS * 2, 60,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(CPU_NUMBERS), r -> new Thread(), (r, executor) -> {
            log.info(((Thread) r).getName() + "任务被直接抛弃了");
        });
    }

    /**
     * 线程池扩展,用用执行
     */
    @NotNull
    @Contract(" -> new")
    public static ThreadPoolExecutor extensionThreadPool() {
        return new ThreadPoolExecutor(CPU_NUMBERS, CPU_NUMBERS * 2, 60, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(CPU_NUMBERS),
                (ThreadFactory) Thread::new) {

            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                super.beforeExecute(t, r);
                log.info("准备执行:" + ((Thread) r).getName());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                log.info("执行完成:" + ((Thread) r).getName());
            }

            @Override
            protected void terminated() {
                super.terminated();
                log.info("线程池退出");
            }
        };
    }

    //public static void main(String[] args) {
    //    //test1();
    //    test2();
    //}

    /**
     * 测试线程池异常打印
     */
    public static void test2() {
        // 装饰者模式增强线程池异常信息栈打印
        ThreadPoolExecutor executor = new TraceThreadPoolExecutor(10, 20, 20, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));
        for (int i = 0; i < CPU_NUMBERS; i++) {
            // submit 无法打印线程池中线程抛出的异常信息，用 execute 方法
            executor.execute(new ExceptionDemo(10, i));
        }
    }

    public static void test1() {
        //ThreadPoolExecutor executor = ThreadPoolUtils.boundedQueue();
        //ThreadPoolExecutor executor = ThreadPoolUtils.unboundedQueue();
        //ThreadPoolExecutor executor = ThreadPoolUtils.noQueue();
        //ThreadPoolExecutor executor = ThreadPoolUtils.rejectHandler();
        ThreadPoolExecutor executor = ThreadPoolUtils.extensionThreadPool();
        //int n = CPU_NUMBERS * 3 + 1;
        int n = CPU_NUMBERS * 3;
        Thread[] threads = new Thread[n];
        for (int i = 0; i < n; i++) {
            threads[i] = new Thread(() -> {
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                    log.info("执行完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "线程" + i);
            executor.execute(threads[i]);
        }
        executor.shutdown();
    }

    /**
     * test2 方法并没发打印异常位置，告知具体的异常位置
     */
    public static class ExceptionDemo extends Thread {

        private int x;

        private int y;

        public ExceptionDemo(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void run() {
            log.info("" + x / y);
        }
    }
}

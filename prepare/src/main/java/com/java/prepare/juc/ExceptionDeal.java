package com.java.prepare.juc;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程池处理异常
 *
 * @author xuweizhi
 * @since 2020/05/08 22:43
 */
@Slf4j
public class ExceptionDeal {

    private ThreadPoolExecutor executor;

    @Before
    public void init() {
        executor = new ThreadPoolExecutor(1, 10, 0, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10), r -> {
            Thread thread = new Thread(r);
            thread.setName("mongodb");
            thread.setUncaughtExceptionHandler((t, e) -> {
                log.info(t.getName() + "抛出一个 bug");
            });
            return thread;
        }, new ThreadPoolExecutor.AbortPolicy());
    }

    @Test
    public void threadPoolExecutorException() {
        ThreadPoolExecutor executors = new ThreadPoolExecutor(10, 20, 0, TimeUnit.MINUTES, new SynchronousQueue<>()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                if (t == null && r instanceof Future<?>) {
                    try {
                        Object result = ((Future<?>)r).get();
                    } catch (CancellationException ce) {
                        t = ce;
                    } catch (ExecutionException ee) {
                        t = ee.getCause();
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt(); // ignore/reset
                    }
                }
                if (t != null)
                    System.out.println(t);
            }
        };

        for (int i = 0; i < 10; i++) {
            executors.submit(() -> {
                if (Math.random() > 0.5) {
                    int x = 1 / 0;
                }
            });
        }

    }

    /**
     * foreach 捕获异常
     */
    @Test
    public void submitTry() {
        ThreadPoolExecutor executors = new ThreadPoolExecutor(10, 20, 0, TimeUnit.MINUTES, new SynchronousQueue<>());
        List<Future<?>> tempList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tempList.add(executors.submit(() -> {
                if (Math.random() > 0.5) {
                    int x = 1 / 0;
                }
            }));
        }
        tempList.forEach(future -> {
            try {
                Object o = future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 直接捕获异常
     */
    @Test
    public void tryCatch() {
        ThreadPoolExecutor executors = new ThreadPoolExecutor(10, 20, 0, TimeUnit.MINUTES, new SynchronousQueue<>());
        for (int i = 0; i < 10; i++) {
            executors.execute(() -> {
                try {
                    if (Math.random() > 0.5) {
                        int i1 = 1 / 0;
                    }
                } catch (Exception e) {
                    log.info("异常");
                }
            });
        }
    }

    /**
     * 线程工厂捕获异常设置
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void e() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                if (Math.random() > 0.5) {
                    int i1 = 1 / 0;
                }
            });
        }
        executor.isShutdown();
    }

    /**
     * VM options: -Xmx8m -Xms8m
     *
     * 会的，newFixedThreadPool使用了无界的阻塞队列LinkedBlockingQueue，如果线程获取一个任务后，任务的执
     * 行时间比较长(比如，上面demo设置了10秒)，会导致队列的任务越积越多，导致机器内存使用不停飙升， 最终导致OOM。
     *
     * FixedThreadPool 适用于处理CPU密集型的任务，确保CPU在长期被工作线程使用的情况下，尽可能的少的分配线程，即适用执行长期的任务。
     *
     */
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    // do nothing
                }
            });
        }
    }

    /**
     * 线程池特点：
     * 核心线程数为0
     * 最大线程数为Integer.MAX_VALUE
     * 阻塞队列是SynchronousQueue
     * 非核心线程空闲存活时间为60秒
     *
     * 当提交任务的速度大于处理任务的速度时，每次提交一个任务，就必然会创建一个线程。极端情况下会创建过多的线程，耗尽 CPU 和内
     * 存资源。由于空闲 60 秒的线程会被终止，长时间保持空闲的 CachedThreadPool 不会占用任何资源。
     */
    @Test
    public void newCachedThreadPool(){
        //提交任务
        //因为没有核心线程，所以任务直接加到SynchronousQueue队列。
        //判断是否有空闲线程，如果有，就去取出任务执行。
        //如果没有空闲线程，就新建一个线程执行。
        //执行完任务的线程，还可以存活60秒，如果在这期间，接到任务，可以继续活下去；否则，被销毁。

    }

    @After
    public void after() throws InterruptedException {
        Thread.sleep(2200);
    }
}

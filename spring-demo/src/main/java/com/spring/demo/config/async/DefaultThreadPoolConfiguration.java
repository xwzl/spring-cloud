package com.spring.demo.config.async;

import lombok.Data;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 异步线程池配置
 *
 * @author xuweizhi
 * @since 2019-09-17
 */
@Data
public abstract class DefaultThreadPoolConfiguration {

    /**
     * 核心线程数：线程池创建时候初始化的线程数
     */
    private int corePoolSize;

    /**
     * 最大线程数：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
     */
    private int maxPoolSize;
    /**
     * 缓冲队列200：用来缓冲执行任务的队列
     */
    private int queueCapacity;

    /**
     * 允许线程的空闲时间(单位：秒)：当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
     */
    private int keepAliveSeconds;

    /**
     * 线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
     */
    private String threadNamePrefix;

    /**
     * 任务等待时间：超时会抛弃任务
     */
    private int awaitTime;

    protected AsyncTaskExecutor taskExecutor() {
        init();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix(threadNamePrefix);

        /*线程池对拒绝任务（无线程可用）的处理策略。这里采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，该策略会直接
        在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务。还有一个是AbortPolicy策略：处理
        程序遭到拒绝将抛出运行时RejectedExecutionException。
        而在一些场景下，若需要在关闭线程池时等待当前调度任务完成后才开始关闭，可以通过简单的配置，进行优雅的停机策略配置。关键就
        是通过setWaitForTasksToCompleteOnShutdown(true) 和setAwaitTerminationSeconds方法。*/
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务都完成再继续销毁其他的Bean
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
        executor.setAwaitTerminationSeconds(awaitTime);
        executor.initialize();
        return executor;
    }

    /**
     * 注册线程池
     *
     * @return 线程池
     */
    protected abstract AsyncTaskExecutor messageExecutor();

    /**
     * 核心线程池大小
     *
     * @return 核心线程池大小
     */
    protected abstract int initCorePoolSize();

    /**
     * 缓冲队列大小
     *
     * @return 缓冲队列大小
     */
    protected abstract int initQueueCapacity();

    /**
     * 保持活跃时间
     *
     * @return 保持活跃时间
     */
    protected abstract int initKeepAliveSeconds();

    /**
     * 线程名的前缀
     *
     * @return 线程名的前缀
     */
    protected abstract String initThreadNamePrefix();

    /**
     * 任务等待时间
     *
     * @return 哈哈
     */
    protected abstract int initAwaitTime();

    /**
     * 设置线程池默认值
     */
    private void init() {
        if (corePoolSize <= 0) {
            corePoolSize = initCorePoolSize();
        }
        if (maxPoolSize <= 0) {
            maxPoolSize = Runtime.getRuntime().availableProcessors() + 1;
            if (corePoolSize > maxPoolSize) {
                corePoolSize = maxPoolSize;
            }
        }
        if (queueCapacity <= 0) {
            queueCapacity = initQueueCapacity();
        }
        if (keepAliveSeconds <= 0) {
            keepAliveSeconds = initKeepAliveSeconds();
        }
        if (threadNamePrefix == null) {
            threadNamePrefix = initThreadNamePrefix();
        }
        if (awaitTime <= 0) {
            awaitTime = initAwaitTime();
        }
    }
}

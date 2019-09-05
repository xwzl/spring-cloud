package com.spring.demo.config.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 这里有几点需要注意下：
 * <p>
 * 在默认情况下，未设置TaskExecutor时，默认是使用SimpleAsyncTaskExecutor这个线程池，但此线程不是真正意义上的
 * 线程池，因为线程不重用，每次调用都会创建一个新的线程。可通过控制台日志输出可以看出，每次输出线程名都是递增的。
 * <p>
 * 调用的异步方法，不能为同一个类的方法，简单来说，因为Spring在启动扫描时会为其创建一个代理类，而同类调用时，还是调用
 * 本身的代理类的，所以和平常调用是一样的。其他的注解如@Cache等也是一样的道理，说白了，就是Spring的代理机制造成的。
 *
 * @author xuweizhi
 */
@Configuration
@EnableAsync
public class ExecutorConfig {


    /**
     * 异步调用线程池配置
     */
    @Bean(name = "asyncExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(15);
        executor.setQueueCapacity(25);
        executor.setKeepAliveSeconds(200);
        executor.setThreadNamePrefix("async-");
        /*线程池对拒绝任务（无线程可用）的处理策略。这里采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，该策略会直接
        在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务。还有一个是AbortPolicy策略：处理
        程序遭到拒绝将抛出运行时RejectedExecutionException。
        而在一些场景下，若需要在关闭线程池时等待当前调度任务完成后才开始关闭，可以通过简单的配置，进行优雅的停机策略配置。关键就
        是通过setWaitForTasksToCompleteOnShutdown(true) 和setAwaitTerminationSeconds方法。*/
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务都完成再继续销毁其他的Bean
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();
        return executor;
    }


}
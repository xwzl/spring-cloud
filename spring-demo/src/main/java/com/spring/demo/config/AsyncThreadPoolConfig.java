// package com.spring.demo.config;
//
// import com.alibaba.ttl.threadpool.TtlExecutors;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.scheduling.annotation.AsyncConfigurer;
// import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
// import java.util.concurrent.Executor;
// import java.util.concurrent.ThreadPoolExecutor;
//
// /**
//  * 异步线程池配置
//  *
//  * @author xuweizhi
//  */
// @Slf4j
// @Configuration
// public class AsyncThreadPoolConfig implements AsyncConfigurer {
//
//     @Value("${spring.sync.corePoolSize:16}")
//     private Integer corePoolSize;
//
//     @Value("${spring.sync.maxPoolSize:32}")
//     private Integer maxPoolSize;
//
//     @Value("${spring.sync.keepAliveSeconds:200}")
//     private Integer keepAliveSeconds;
//
//     @Value("${spring.sync.queueCapacity:128}")
//     private Integer queueCapacity;
//
//
//     @Bean(name = "asyncExecutor")
//     public ThreadPoolTaskExecutor asyncThreadPoolTaskExecutor() {
//         ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
//         // 核心线程数
//         threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
//         // 最大线程数,只有在缓冲队列满了之后才会申请超过核心线程数的线程
//         threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
//         // 缓存队列
//         threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
//         // 线程空闲时间,当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
//         threadPoolTaskExecutor.setKeepAliveSeconds(keepAliveSeconds);
//         // 异步方法内部线程名称
//         threadPoolTaskExecutor.setThreadNamePrefix("async-");
//         threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//         threadPoolTaskExecutor.initialize();
//
//         return threadPoolTaskExecutor;
//     }
//
//     /**
//      * 指定默认线程池，使用@Async注解时，不指定线程池名称，默认使用此线程池
//      */
//     @Override
//     public Executor getAsyncExecutor() {
//         log.info("初始化异步执行默认线程池");
//         return TtlExecutors.getTtlExecutor(asyncThreadPoolTaskExecutor());
//     }
//
//     @Override
//     public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
//         return ((ex, method, params) -> log.error("线程池执行任务发送时出现异常：", ex));
//     }
// }

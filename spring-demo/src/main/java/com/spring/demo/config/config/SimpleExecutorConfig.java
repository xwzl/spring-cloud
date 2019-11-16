package com.spring.demo.config.config;

import com.spring.demo.config.async.DefaultThreadPoolConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.task.AsyncTaskExecutor;

/**
 * 基础线程池配置，貌似注入支持哪个啥
 *
 * @author xuweizhi
 * @since 2019-09-17
 */
@Configuration
@PropertySource(value = {"classpath:async.properties"})
@ConfigurationProperties(prefix = "simple")
public class SimpleExecutorConfig extends DefaultThreadPoolConfiguration {

    @Override
    @Bean("simpleExecutor")
    public AsyncTaskExecutor messageExecutor() {
        return taskExecutor();
    }

    @Override
    protected int initCorePoolSize() {
        return 6;
    }

    @Override
    protected int initQueueCapacity() {
        return 80;
    }

    @Override
    protected int initKeepAliveSeconds() {
        return 20;
    }

    @Override
    protected String initThreadNamePrefix() {
        return "simple_async_executor";
    }

    @Override
    protected int initAwaitTime() {
        return 60;
    }
}

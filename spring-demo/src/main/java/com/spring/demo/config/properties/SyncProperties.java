package com.spring.demo.config.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuweizhi
 * @since 2019/09/11 10:01
 */
@Configuration
@ConfigurationProperties(prefix = "sync.pool")
@Data
@ToString
public class SyncProperties {

    /**
     * 核心线程池大小
     */
    private Integer coreSize;

    /**
     * 最大线程数
     */
    private Integer maxSize;

    /**
     * 队列容量
     */
    private Integer queueCapacity;

    /**
     * 超过核心线程数的线程完成任务后存活的时间
     */
    private Integer aliveTime;

    /**
     * 等待任务的时间
     */
    private Integer awaitTime;

}

package com.java.prepare.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.common.model.handler.MybatisFieldHandler;

/**
 * Mybatis 配置
 *
 * @author xuweizhi
 * @since 2020/05/27 14:16
 */
@Configuration
@MapperScan("com.java.prepare.mapper")
public class MybatisConfig {

    /**
     * mybatis 公共字段注入
     */
    @Bean
    public MybatisFieldHandler mybatisFieldHandler() {
        return new MybatisFieldHandler();
    }

    /// **
    // * 配置 mybatis 插件
    // */
    // @Bean
    // public PerformanceInterceptor performanceInterceptor() {
    // //SQLStatsInterceptor sqlStatsInterceptor = new SQLStatsInterceptor();
    // //// 设置参数，比如阈值等，可以在配置文件中配置，这里直接写死便于测试
    // //Properties properties = new Properties();
    // //// 这里设置慢查询阈值为1毫秒，便于测试
    // //properties.setProperty("time", "1");
    // //sqlStatsInterceptor.setProperties(properties);
    // return new PerformanceInterceptor();
    // }

}

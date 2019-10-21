package com.spring.demo.config.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 数据库连接池配置
 *
 * @author xuweizhi
 * @since 2019/04/22 21:48
 */
@Configuration
public class DataSourcesConfig {

    /**
     * 虽然我们配置了druid连接池的其它属性，但是不会生效。因为默认是使用的java.sql.Datasource的类来获取属性的，
     * 有些属性datasource没有。如果我们想让配置生效，需要手动创建Druid的配置文件。
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }
}
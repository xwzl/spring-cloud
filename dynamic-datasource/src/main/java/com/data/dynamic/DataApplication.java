package com.data.dynamic;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 必须排除 DruidDataSourceAutoConfigure 数据源
 *
 * @author xuweizhi
 * @since 2019/06/04 14:41
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@MapperScan("com.data.dynamic.mapper")
public class DataApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class, args);
    }
}

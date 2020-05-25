package com.java.prepare;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xuweizhi
 * @since 2020/05/15 22:39
 */
@EnableSwagger2
@SpringBootApplication
@MapperScan("com.java.prepare.mapper")
@ComponentScan({"com.spring.common", "com.java.prepare"})
public class PrepareApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrepareApplication.class, args);
    }
}

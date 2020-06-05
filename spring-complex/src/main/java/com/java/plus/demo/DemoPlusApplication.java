package com.java.plus.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xuweizhi
 * @since 2019/10/10 16:48
 */
@EnableSwagger2
@SpringBootApplication
public class DemoPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoPlusApplication.class, args);
    }
}

package com.java.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xuweizhi
 * @since 2019/07/16 15:29
 */
@SpringBootApplication
@EnableSwagger2
public class ElasticApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticApplication.class, args);
    }

}

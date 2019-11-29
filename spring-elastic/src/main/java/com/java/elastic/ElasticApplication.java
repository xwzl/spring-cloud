package com.java.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xuweizhi
 * @since 2019/07/16 15:29
 */
@EnableSwagger2
@SpringBootApplication
@EnableAspectJAutoProxy
public class ElasticApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticApplication.class, args);
    }

}

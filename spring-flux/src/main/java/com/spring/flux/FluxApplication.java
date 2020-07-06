package com.spring.flux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Flux 启动类
 *
 * @author xuweizhi
 * @since 2019/12/03 17:11
 */
@EnableSwagger2
@SpringBootApplication
public class FluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(FluxApplication.class, args);
    }
}

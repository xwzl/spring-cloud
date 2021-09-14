package com.spring.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Spring Cache Application
 *
 * @author xuweizhi
 * @since 2021/08/03 17:19
 */
@EnableCaching
@EnableSwagger2WebMvc
@SpringBootApplication
public class CacheApplication {

    public static void main(String[] args){
        SpringApplication.run(CacheApplication.class,args);
    }
}

package com.spring.demo.config;

import com.spring.demo.config.condition.DemoCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @author xuweizhi
 * @since 2019/07/26 16:12
 */
@Configuration
public class BeanConfig {

    @Conditional({DemoCondition.class})
    @Bean("bill")
    public Date bill() {
        return new Date();
    }

    @Conditional({DemoCondition.class})
    @Bean("bills")
    public Date bills() {
        return new Date();
    }
}

package com.spring.demo.config.config;

import com.spring.demo.config.condition.DemoCondition;
import com.spring.demo.model.dos.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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

    /**
     *  这个注解的作用随时当前项目有其它 bean ,则使用这个 bean ，没有就不使用吗？
     */
    @Bean
    @ConditionalOnBean
    public User user() {
        return new User();
    }

    @Bean("user")
    public User getUser() {
        User user = new User();
        user.setAge("11111");
        return user;
    }

    /**
     * 当前项目没有这个 bean 的配置，使用这个 bean，有则使用其它 bean
     */
    @Bean
    @ConditionalOnMissingBean
    public User use1r() {
        return new User();
    }

    @Bean("user1")
    public User getUser1() {
        User user = new User();
        user.setAge("11111");
        return user;
    }
}

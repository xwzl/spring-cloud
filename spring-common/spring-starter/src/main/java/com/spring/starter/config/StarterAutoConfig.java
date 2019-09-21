package com.spring.starter.config;

import com.spring.common.model.Apple;
import com.spring.starter.model.ConditionalSample;
import com.spring.starter.model.YoungerModel;
import com.spring.starter.properties.DocumentMissingClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 自动注入 BaseProperties 对象
 *
 * @author xuweizhi
 * @since 2019-08-23
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({YoungerModel.class})
public class StarterAutoConfig {

    /**
     * 当配置项前缀 sample.conditional ，属性  storeType；
     * 当 com.security.oauth2.storeType=jwt是配置才会生效，matchIfMissing 配置文件中不写这个属性，这个配置会失效
     */
    @Bean
    @ConditionalOnProperty(prefix = "sample.conditional", name = "hello", havingValue = "张杰", matchIfMissing = true)
    public ConditionalSample conditionalSample() {
        log.info("sample.conditional.hello=张杰,spring-starter 配置的 bean 生效");
        return new ConditionalSample();
    }

    /**
     * 什么时候使用 name,什么时候使用 value ? name : 不确定指定类在classpath 上,value : 确定指定类在 classpath 上
     * 注解中的属性可以直接输入字符串
     */
    @Bean
    @ConditionalOnClass(value = Apple.class)
    public DocumentMissingClass documentMissingBeanValue() {
        return new DocumentMissingClass() {
            @PostConstruct
            public void init() {
                log.info("当前上下文中包含这个类路径才会被初始化,这是在 spring-starter 定义的 bean,指定 value = Apple.class");
            }
        };
    }

    @Bean
    @ConditionalOnClass(name = "com.spring.common.model.Apple")
    public DocumentMissingClass documentMissingBeanName() {
        return new DocumentMissingClass() {
            @PostConstruct
            public void init() {
                log.info("当前上下文中包含这个类路径才会被初始化,这是在 spring-starter 定义的 bean ,指定 name = com.spring.common.model.Apple");
            }
        };
    }

}

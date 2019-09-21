package com.spring.demo.config.config;

import com.spring.demo.config.properties.Document;
import com.spring.demo.config.properties.DocumentMissingBean;
import com.spring.starter.properties.DocumentMissingClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author xuweizhi
 * @since 2019/09/21 14:23
 */
@Slf4j
@Configuration
public class ConditionalConfig {

    /**
     * 覆盖 {@link ConditionalDefaultConfig#conditionalOnMissingBean()},通过 bean 中添加值做 bean 无效
     */
    @Bean
    public DocumentMissingBean conditionalOnMissingBean() {
        DocumentMissingBean documentMissingBean = new DocumentMissingBean() {
            @PostConstruct
            public void init() {
                log.info("容器中有定义的 conditionalOnMissingBean，conditionalOnBeanDefault() 失效");
            }
        };
        documentMissingBean.setName("conditionalOnMissingBean");
        return documentMissingBean;
    }

    /**
     * Bean 与 Bean 之间依赖注入，只用当 @ConditionalOnBean() 注解中类对对应的 bean 被注入后，才调用这个方法
     * <p>
     * 只有当 {@link ConditionalConfig#conditionalOnMissingBean()} 加入 IoC 容器中且在此方法后面，才调用这个方法
     */
    @Bean
    @ConditionalOnBean(DocumentMissingBean.class)
    public Document conditionalOnBean() {
        return new Document() {
            @PostConstruct
            public void init() {
                log.info("如果 Spring 上下文中包含 DocumentMissingProperties 的 bean，这个 bean 会被添加到 IoC 中");
            }
        };
    }

    /**
     * com.spring.common.model.Apple1 不包含在上下文，因此不会创建 bean
     */
    @Bean
    @ConditionalOnClass(name = "com.spring.common.model.Apple1")
    public DocumentMissingClass foreverNoCreated() {
        return new DocumentMissingClass() {
            @PostConstruct
            public void init() {
                log.info("如果引入了 spring-model 模块，com.spring.common.model.Apple 在上下文中，这个 bean 被创建");
            }
        };
    }

}

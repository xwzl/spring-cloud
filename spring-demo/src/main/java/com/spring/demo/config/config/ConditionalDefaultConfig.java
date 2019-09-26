package com.spring.demo.config.config;

import com.spring.common.model.Apple;
import com.spring.demo.config.properties.Document;
import com.spring.demo.config.properties.DocumentMissingBean;
import com.spring.starter.properties.DocumentMissingClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Spring Conditional 条件注解测试
 *
 * @author xuweizhi
 * @since 2019/09/21 14:23
 */
@Slf4j
@Configuration
public class ConditionalDefaultConfig {

    /**
     * 系统中没有这个bean,则默认使用这个 bean
     */
    @Bean
    @ConditionalOnMissingBean
    public DocumentMissingBean conditionalOnMissingBean() {
        DocumentMissingBean documentMissingBean = new DocumentMissingBean() {
            @PostConstruct
            public void init() {
                log.info("If there is no conditionalOnMissingBean in the app, use this default configuration");
            }
        };
        documentMissingBean.setName("conditionalOnMissingBean");
        return documentMissingBean;
    }

    /**
     * 如果这个 bean 放在 @ConditionalOnMissingBean，无论如何都会被初始化，相当于 @ConditionalOnBean 条件失效
     */
    @Bean
    @ConditionalOnBean(DocumentMissingBean.class)
    public Document conditionalOnBeanDefault() {
        return new Document() {
            @PostConstruct
            public void init() {
                log.info("@Conditional is invalid");
            }
        };
    }

    /**
     * name : 不确定指定类在classpath 上
     */
    @Bean
    @ConditionalOnClass(name = "com.spring.common.model.Apple")
    public DocumentMissingClass documentMissingBeanNameApple() {
        return new DocumentMissingClass() {
            @PostConstruct
            public void init() {
                log.info("If the spring-model module is introduced, com.spring.common.model.Apple is created in the context of this bean");
            }
        };
    }

    /**
     * value : 确定指定类在 classpath 上,最好在被依赖模块的添加 bean
     */
    @Bean
    @ConditionalOnClass(value = Apple.class)
    public DocumentMissingClass documentMissingBeanValueApple() {
        return new DocumentMissingClass() {
            @PostConstruct
            public void init() {
                log.info("If the spring-model module is introduced, Apple.class is created in the context, this bean is created");
            }
        };
    }

    /**
     * 包含 com.spring.model.Mix 类路径，因此被初始化
     */
    @Bean
    @ConditionalOnMissingClass("com.spring.common.model.Apple")
    @ConditionalOnProperty(prefix = "sync.pool", name = "core-size", havingValue = "10")
    public DocumentMissingClass conditionalOnMissingClass() {
        return new DocumentMissingClass() {
            @PostConstruct
            public void init() {
                log.info("The context contains com.spring.common.model.Apple and will not be initialized");
            }
        };
    }

    /**
     * 不包含 com.spring.model.Mix 类路径，因此被初始化
     */
    @Bean
    @ConditionalOnMissingClass("com.spring.model.Mix")
    public DocumentMissingClass conditionalOnMissingClassValue() {
        return new DocumentMissingClass() {
            @PostConstruct
            public void init() {
                log.info("com.spring.model.Mix is not included in the context, so it is initialized");
            }
        };
    }

    /**
     * 属性条件，havingValue = 10 没有效果，有且仅有这个属性存在且值为 10 的时候才会创建 bean
     */
    @Bean
    @ConditionalOnProperty(prefix = "sync.pool", name = "core-size", havingValue = "10", matchIfMissing = true)
    public DocumentMissingClass conditionalOnProperty() {
        return new DocumentMissingClass() {
            @PostConstruct
            public void init() {
                log.info("Sync.pool.core-size=10 exists in the configuration, so it is initialized");
            }
        };
    }
}

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
                log.info("如果应用中没有 conditionalOnMissingBean，则使用这个默认配置！");
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
                log.info("@Conditional 注解失效");
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
                log.info("如果引入了 spring-model 模块，com.spring.common.model.Apple 在上下文中，这个 bean 被创建");
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
                log.info("如果引入了 spring-model 模块，Apple.class 在上下文中，这个 bean 被创建");
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
                log.info("上下文中包含com.spring.common.model.Apple,因此不会被初始化");
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
                log.info("上下文中不包含 com.spring.model.Mix,因此被初始化");
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
                log.info("sync.pool.core-size=10 所以被初始化");
            }
        };
    }
}

package com.spring.demo.config.config;

import com.spring.demo.config.properties.Document;
import com.spring.demo.config.properties.DocumentMissingBean;
import com.spring.demo.untils.PropertiesUtil;
import com.spring.starter.properties.DocumentMissingClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

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
                log.info("There is a defined conditionalOnMissingBean in the container, conditionalOnBeanDefault bean is invalid");
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
        String prop = PropertiesUtil.PROP;
        return new Document() {
            @PostConstruct
            public void init() {
                log.info("If the Spring context contains a bean for DocumentMissingProperties, the bean will be added to IoC" + prop);
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
                log.info("If the spring-model module is introduced, com.spring.common.model.Apple is created in the context of this bean.");
            }
        };
    }

}

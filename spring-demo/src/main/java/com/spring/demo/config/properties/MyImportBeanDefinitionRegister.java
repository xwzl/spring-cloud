package com.spring.demo.config.properties;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 通过添加 bean 的信息方式生成 bean
 *
 * @author xuweizhi
 * @since 2019/09/21 14:01
 */
public class MyImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(ImportBeanRegister.class);
        registry.registerBeanDefinition("importBeanRegister", rootBeanDefinition);
    }
}

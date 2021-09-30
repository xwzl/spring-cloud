package com.spring.framework.life.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * BeanDefinition 加载阶段,可以修改 bean 注册信息.
 *
 * @author xuweizhi
 * @since 2021/09/29 17:05
 */
@Slf4j
@Component
public class SpringBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    /**
     * 调用我们的后置处理器{@link org.springframework.context.support.PostProcessorRegistrationDelegate#invokeBeanFactoryPostProcessors}
     * <p>
     * 大概在 236 行
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition student = beanFactory.getBeanDefinition("student");
        log.info("处理 BeanFactory 的后置处理器----------------------------------");
    }
}

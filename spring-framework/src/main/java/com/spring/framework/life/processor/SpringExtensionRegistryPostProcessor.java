package com.spring.framework.life.processor;

import com.spring.framework.model.CustomizeBeanDefinitionRegistry;
import com.spring.framework.model.School;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Address;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * Spring Registry Post Processor 扩展: 注册 BeanDefinition
 * <p>
 * Spring支持我们通过代码来将指定的类注册到spring容器中。
 *
 * @author xuweizhi
 * @since 2021/09/29 17:05
 */
@Slf4j
@Component
public class SpringExtensionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        // 创建一个bean的定义类的对象
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(CustomizeBeanDefinitionRegistry.class);
        // 将Bean 的定义注册到Spring环境
        registry.registerBeanDefinition("customizeBeanDefinitionRegistry", rootBeanDefinition);
        log.info("将 CustomizeBeanDefinitionRegistry 注册到 Spring Bean 定义中---------------------------------");
    }

    /**
     * Spring容器初始化时，从资源中读取到bean的相关定义后，保存在BeanDefinitionMap，在实例化bean的操作就是依据这些bean的定义来做的，而在实例化之前，Spring允许
     * 我们通过自定义扩展来改变bean的定义，定义一旦变了，后面的实例也就变了，而beanFactory后置处理器，即BeanFactoryPostProcessor就是用来改变bean定义的。
     * <p>
     * 调用我们的后置处理器{@link org.springframework.context.support.PostProcessorRegistrationDelegate#invokeBeanFactoryPostProcessors}
     *
     * @param beanFactory bean 工厂
     * @throws BeansException 异常
     */
    @Override
    @SuppressWarnings("all")
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        // 获得bean定义
        RootBeanDefinition student = (RootBeanDefinition) beanFactory.getBeanDefinition("student");

        // 修改Class
        //student.setBeanClass(Tank.class);

        //设置默认值 相当于xml:<property name = "school" value = "大学" / >
        student.getPropertyValues().addPropertyValue(new PropertyValue("school", new School("学校", new Address())));

        //设置构造函数 相当于xml:<constructor - arg index = "0" value = "大学" / >
        //student.getConstructorArgumentValues().addIndexedArgumentValue(0, "大学");

        //自动装配方式
        //student.setAutowireMode(AUTOWIRE_BY_NAME);

        //强制依赖检查：
        //student.setDependencyCheck(AbstractBeanDefinition.DEPENDENCY_CHECK_ALL);
        student.setLazyInit(true);

        student.setInitMethodName("initMethod");
    }
}

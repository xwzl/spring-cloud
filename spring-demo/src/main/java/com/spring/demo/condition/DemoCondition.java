package com.spring.demo.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 测试条件注入 Bean,配置类使用 @Condition 注解标志需要校验的 bean
 *
 * @author xuweizhi
 * @since 2019/07/26 16:06
 */
public class DemoCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
        ClassLoader classLoader = conditionContext.getClassLoader();
        Environment environment = conditionContext.getEnvironment();
        // 获取 bean 定义的名称
        BeanDefinitionRegistry registry = conditionContext.getRegistry();
        String[] names = registry.getBeanDefinitionNames();
        for (String name : names) {
            if (name.equals("bill")) {
                return false;
            }
            if(name.equals("bills")){
                return false;
            }
        }
        return true;
    }

}

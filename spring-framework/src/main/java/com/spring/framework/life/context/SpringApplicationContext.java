package com.spring.framework.life.context;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 *
 * @author xuweizhi
 * @since 2021/09/30 13:09
 */
public class SpringApplicationContext extends AnnotationConfigApplicationContext {


    public SpringApplicationContext(Class<?>... componentClasses) {
        super(componentClasses);
    }

    /**
     * 子类容器的扩展
     */
    @Override
    protected void initPropertySources() {
        //获取环境
        ConfigurableEnvironment environment = getEnvironment();
        //设置必须属性名集合
        environment.setRequiredProperties("aa", "bb");
    }

}

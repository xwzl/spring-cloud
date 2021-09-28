package com.spring.framework.life;

import com.spring.framework.life.imports.CustomizeImportSelector1;
import com.spring.framework.life.imports.CustomizeImportSelector2;
import com.spring.framework.life.imports.CustomizeImportSelector3;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 我们在基于XML的配置部分详细已经介绍过bean的生命周期回调功能，详情看那一部分。@PostConstruct和@PreDestroy是来自JSR-250的bean
 * 生命周期注解，在 Spring 2.5 开始同样引入了对这些注解的支持，同样使用 CommonAnnotationBeanPostProcessor 处理器来解析这两个注
 * 解，component-scan默认将CommonAnnotationBeanPostProcessor已经注册了。
 * <p>
 * 注解@PostConstruct表示初始化bean回调，@PreDestroy表示销毁bean回调。和Resource一样，这三个注解是 JDK 6 到 8 的标准 Java 库的
 * 一部分，位于rt.jar包下的java.annotation包中。使用时无需引入任何依赖。但是JDK9及其之后的版本，javax.annotation脱离了Java核心库，
 * 想要使用时只能引入maven依赖：javax.annotation-api（artifactId）。
 *
 * @author xuweizhi
 * @since 2020/12/15 16:03
 */
@Component
@Import({CustomizeImportSelector1.class, CustomizeImportSelector2.class, CustomizeImportSelector3.class})
public class BeanCallback {

    /**
     * 单例bean
     * 可以进行bean创建回调和bean销毁回调
     */
    @Component("beanCallbackA")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public static class BeanCallbackA {

        @PostConstruct
        public void postConstruct() {
            System.out.println("BeanCallbackA bean创建回调");
        }

        @PreDestroy
        public void preDestroy() {
            System.out.println("BeanCallbackA bean销毁回调");
        }

        public BeanCallbackA() {
            System.out.println("BeanCallbackA 构造器");
        }

    }


    /**
     * 原型bean
     * 只能进行bean创建回调，不会进行bean销毁回调
     */
    @Component("beanCallbackB")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class BeanCallbackB {

        @PostConstruct
        public void postConstruct() {
            System.out.println("BeanCallbackB bean创建回调");
        }

        @PreDestroy
        public void preDestroy() {
            System.out.println("BeanCallbackB bean销毁回调");
        }

        public BeanCallbackB() {
            System.out.println("BeanCallbackB 构造器");
        }
    }

    public static void main(String[] args) {
        System.out.println("---创建容器---");
        //新建容器，将会实例化单例的bean，触发bean创建回调
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanCallback.class);
        System.out.println("---获取bean实例---");
        //获取bean实例，将会实例化原型的bean，触发bean创建回调
        System.out.println(applicationContext.getBean("beanCallbackA", BeanCallbackA.class));
        System.out.println(applicationContext.getBean("beanCallbackB", BeanCallbackB.class));
        //关闭容器，将会销毁bean，触发单例bean的销毁回调
        System.out.println("---容器关闭---");
        applicationContext.close();
    }
}

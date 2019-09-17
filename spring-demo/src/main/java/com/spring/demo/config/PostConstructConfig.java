package com.spring.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * PostConstruct 注释用于在依赖关系注入完成之后需要执行的方法上，以执行任何初始化。此方法必须在将类放入服务之前调用。
 * 支持依赖关系注入的所有类都必须支持此注释。即使类没有请求注入任何资源，用 PostConstruct 注释的方法也必须被调用。只
 * 有一个方法可以用此注释进行注释。应用 PostConstruct 注释的方法必须遵守以下所有标准：该方法不得有任何参数，除非是在
 * EJB 拦截器 (interceptor) 的情况下，根据 EJB 规范的定义，在这种情况下它将带有一个 InvocationContext 对象 ；该方
 * 法的返回类型必须为 void；该方法不得抛出已检查异常；应用 PostConstruct 的方法可以是 public、protected、package
 * private 或 private；除了应用程序客户端之外，该方法不能是 static；该方法可以是 final；如果该方法抛出未检查异常，
 * 那么不得将类放入服务中，除非是能够处理异常并可从中恢复的 EJB。
 *
 * @author xuweizhi
 * @since 2019-07-17
 */
@Component
@Slf4j
public class PostConstructConfig {

    /**
     * 设置日期格式,精确到毫秒
     */
    private SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");

    /**
     * 被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次，类似于 Servlet 的init()方法。
     * 被@PostConstruct修饰的方法会在构造函数之后，init()方法之前运行。
     */
    @PostConstruct
    public void someMethod() {
        log.info("时间：" + df.format(new Date()) + "执行@PostConstruct修饰的someMethod()方法...");
    }

    /**
     * 被@PreConstruct修饰的方法会在服务器卸载Servlet的时候运行，并且只会被服务器调用一次，类似于Servlet的destroy()方法。
     * 被@PreConstruct修饰的方法会在destroy()方法之后运行，在Servlet被彻底卸载之前。
     */
    @PreDestroy
    public void otherMethod() {
        log.info("时间：" + df.format(new Date()) + "执行@PreDestroy修饰的otherMethod()方法...");
    }

}
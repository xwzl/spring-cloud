package com.java.prepare;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author xuweizhi
 * @since 2020/05/15 22:39
 */
@EnableSwagger2WebMvc
@SpringBootApplication
@MapperScan("com.java.prepare.mapper")
public class PrepareApplication {

    public static void main(String[] args) {
        disableAccessWarnings();
        ConfigurableApplicationContext run = SpringApplication.run(PrepareApplication.class, args);
        //ConfigurableListableBeanFactory beanFactory = run.getBeanFactory();
        //BeanDefinition mysql = beanFactory.getBeanDefinition("mysqlController");
        //System.out.println("");
    }

    /**
     * 忽略非法反射警告  适用于jdk11
     */
    @SuppressWarnings("unchecked")
    private static void disableAccessWarnings() {
        try {
            Class unsafeClass = Class.forName("sun.misc.Unsafe");
            Field field = unsafeClass.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Object unsafe = field.get(null);

            Method putObjectVolatile = unsafeClass.getDeclaredMethod("putObjectVolatile", Object.class, long.class, Object.class);
            Method staticFieldOffset = unsafeClass.getDeclaredMethod("staticFieldOffset", Field.class);

            Class loggerClass = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field loggerField = loggerClass.getDeclaredField("logger");
            Long offset = (Long) staticFieldOffset.invoke(unsafe, loggerField);
            putObjectVolatile.invoke(unsafe, loggerClass, offset, null);
        } catch (Exception ignored) {
        }
    }
}

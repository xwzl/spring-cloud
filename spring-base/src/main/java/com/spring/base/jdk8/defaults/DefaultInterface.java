package com.spring.base.jdk8.defaults;

import lombok.extern.slf4j.Slf4j;

/**
 * JDK 8 接口测试
 *
 * @author xuweizhi
 * @since 2019/09/16 13:57
 */
@FunctionalInterface
public interface DefaultInterface {

    String PARENT_FIELD = "父类属性";

    Thread THREAD = new Thread() {
        {
            System.out.println("我被初始化了" + DefaultInterface.class.getSimpleName());
        }
    };

    /**
     * 静态方法与默认方法会影响函数式编程接口？
     */
    void function();

    /**
     * 接口静态方法测试，派生接口不会记性此类
     */
    static void staticMethod() {
        System.out.println("This is a static method!");
    }

    default void defaultMethod() {
        System.out.println("This is a default method with " + DefaultInterface.class.getSimpleName());
    }
}

@Slf4j
class DefaultImpl {


    public static void main(String[] args) {

        //parentStaticTest();

        //childStaticTest();

        defaultMethodTest(() -> log.info("函数式编程实现!可以调用静态方法"));

        staticMethod(new Child() {
            @Override
            public void function() {
                System.out.println("出乎意料之外啊！调用接口的方法会初始化接口以及父接口的静态变量");
            }
        });

        coverInterfaceDefaultImpl(new DefaultInterface() {
            @Override
            public void function() {

            }

            @Override
            public void defaultMethod() {
                log.info("覆盖默认方法的实现！");
            }
        });

        // 默认方法冲突问题
        defaultConflict(System.out::println);

    }

    /**
     * 默认方法冲突,{@link Complex}
     */
    private static void defaultConflict(Complex complex) {
        complex.defaultMethod();
    }

    /**
     * 实现类覆盖默认方法
     */
    private static void coverInterfaceDefaultImpl(DefaultInterface defaultInterface) {
        defaultInterface.defaultMethod();
    }

    /**
     * 哈哈
     */
    private static void defaultMethodTest(DefaultInterface defaultInterface) {
        defaultInterface.defaultMethod();
        defaultInterface.function();
        DefaultInterface.staticMethod();
    }

    /**
     * 调用父类 静态几口失败
     */
    private static void staticMethod(Child child) {
        child.defaultMethod();
        child.function();
        //Child.staticMethod();
    }

    /**
     * 测试结果大出意料之外，接口的静态变量只有真正使用时才会初始化，其实现类初始化根本不会初始化接口或者实现类的接口的父接口
     */
    private static void parentStaticTest() {
        Thread thread = DefaultInterface.THREAD;
    }

    /**
     * 子类静态变量测试,并不会初始化父接口的静态变量
     */
    private static void childStaticTest() {
        log.info(Child.CHILD_FIELD);
    }

}

/**
 * 测试引用子类静态变量不会初始化福接口的静态变量以及静态接口不可继承的问题
 */
interface Child extends DefaultInterface {
    String CHILD_FIELD = "子类静态变量";
}

/**
 * 默认方法冲突问题：
 * <p>
 * 场景：一个类实现多个接口且其中有两个或者两个以上的默认接口相同，其实现类必须实现默认方法
 */
interface DefaultConflictInterface {

    default void defaultMethod() {
        System.out.println("This is a default method with " + DefaultConflictInterface.class.getSimpleName());
    }
}

/**
 * ......
 */
interface Complex extends DefaultInterface, DefaultConflictInterface {

    @Override
    default void defaultMethod() {
        System.out.println("默认方法冲突，继承接口或者直接实现类必须实现默认方法！");
    }

}

/**
 * 派生接口静态变量引用不会初始化父接口的原因是--静态属性修饰符为 final
 */
@Slf4j
class ThreadDemo {
    public ThreadDemo() {
        new Thread(() -> log.info("测试派生接口引用静态变量不会初始化父类接口静态变量！")).start();
    }
}

package com.spring.base.jdk8;

/**
 * @author xuweizhi
 * @since 2019/09/16 14:57
 */
public class Apple {

    private Apple() {
        System.out.println("初始化");
    }

    /**
     * 首先 getInstance() 方法中没有锁，这使得在高并发环境下性能优越。其次，只有在 getInstance() 方法第一次被调用时，
     * Single 的实例才会被创建。因此这种方法巧妙地使用了内部类和类的初始化方式。
     * <p>
     * 内部类 Single 被声明为 private ，这使得我们不可能在外部访问并初始化它。而我们只可能在 getInstance() 方法内部
     * 内部对 Single 类进行初始化，利用虚拟机的类初始化机制创建单例
     */
    private final static class Single {
        private static Apple apple = new Apple();
    }

    private static String str = "测试";

    private final static Apple getInstance() {
        return Single.apple;
    }
}

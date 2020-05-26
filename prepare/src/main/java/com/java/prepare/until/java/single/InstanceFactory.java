package com.java.prepare.until.java.single;

/**
 * JVM 在类的初始化阶段（即在 Class 被加载后，且被线程使用之前），会执行类的初始化。在执行类的初始化期间，
 * JVM 会去获取一个锁。这个锁可以同步多个线程对同一个类的初始化。
 *
 * http://note.youdao.com/noteshare?id=2322111334cfc7c0e7fc4eeea249a8f5&sub=DA140695DB19401F9BAF2B6A971FB702
 */
public class InstanceFactory {

    private static class InstanceHolder {
        public static Instance instance = new Instance();
    }

    // 这里将导致 InstanceHolder 类被初始化
    public static Instance getInstance() {
        return InstanceHolder.instance;
    }
}

package com.spring.base.jdk8;

import lombok.extern.slf4j.Slf4j;

/**
 * 字符串分析
 *
 * @author xuweizhi
 * @since 2019/12/05 13:52
 */
@Slf4j
class StringAnalysis {

    private static String tax = ": + = {} , equals = {}\n";

    /**
     * 字符串拼接 +
     *
     * @param prefix 前缀
     * @param suffix 后缀
     */
    private static void stringPlus(String prefix, String suffix) {
        // 字符串加号拼接
        String directResult = prefix + suffix;
        StringBuilder sb = new StringBuilder();
        sb.append(prefix).append(suffix);
        // 字符串加号拼接相当与 StringBuilder
        String builderResult = sb.toString();
        compareEquals(directResult, builderResult, "字符串 + 与 StringBuilder 拼接两个字符换意义相同");
    }

    private static void compareEquals(String old, String news, String content) {
        log.info(content + tax, old == news, old.equals(news));
    }

    /**
     * 为什么要不可变
     * <p>
     * String 类的源码中还有一个重要的字段 hash，用来保存字符串对象的 hashCode。
     * <p>
     * {@link String#hashCode()}
     * <p>
     * 因为字符串是不可变的，所以一旦被创建，它的 hash 值就不会再改变了。由此字符串非常适合作为 HashMap 的 key 值，这样可以极大地提高效率。
     * <p>
     * 另外呢，不可变对象天生是线程安全的，因此字符串可以在多个线程之间共享。
     * <p>
     * 举个反面的例子，假如字符串是可变的，那么数据库的用户名和密码（字符串形式获得数据库连接）将不再安全，一些高手可以随意篡改，从而导致严重的安全问题。
     * <p>
     * 总结一下，字符串一旦在内存中被创建，就无法被更改。String 类的所有方法都不会改变字符串本身，而是返回一个新的字符串对象。如果需要一个可修改的字符序
     * 列，建议使用 StringBuffer 或 StringBuilder 类代替 String 类，否则每次创建的字新符串对象会导致 Java 虚拟机花费大量的时间进行垃圾回收。
     */
    public static void main(String[] args) {
        // 字符串引用问题
        stringReference();

        String hello = "hello ";
        String world = "world";
        stringPlus(hello, world);

        // new String();
        String old = hello + world;
        String value = new String(hello + world);
        String valueDirect = new String("hello world");
        String oldDirect = "hello world";
        String oldDirectValue = "hello world";

        compareEquals(old, value, "+ 与 new String(value1 + value2)");
        compareEquals(old, valueDirect, "+ 与 new String(result)");
        compareEquals(old, oldDirect, "+ 与 result");
        compareEquals(old, oldDirectValue, "+ 与 result value");
        compareEquals(oldDirect, oldDirectValue, "result + result value");
        compareEquals(valueDirect, value, "result + ");
    }

    /**
     * 字符串引用问题
     */
    private static void stringReference() {
        // 指向元数据堆内空间
        String aliTa = "战斗天使";
        // 相等
        String wang = aliTa;
        log.info("aliTa == wang ? {}", aliTa == wang);
        log.info("aliTa.equals(wang) ? {}", aliTa.equals(wang));
        aliTa = aliTa.concat("hello");
    }
}

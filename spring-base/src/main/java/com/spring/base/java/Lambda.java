package com.spring.base.java;

@FunctionalInterface
interface Print<T> {
    public void print(T x);
}

/**
 * <ul>
 * <li>生成 lambda 表示式代理实现类：-Djdk.internal.lambda.dumpProxyClasses</li> 虚拟机参数
 * <li>查看反编译文件：javap -p lambda.class</li>
 * <li>查看详细反比的文件：javap -p -c lambda.class</li>
 * </ul>
 *
 * http://note.youdao.com/noteshare?id=14b16550e419d81618ce09b512a3b8b8&sub=187F7DEC9064485A89F7DF06252AEE72
 */
public class Lambda {
    public static void PrintString(String s, Print<String> print) {
        print.print(s);
    }

    public static void main(String[] args) {
        PrintString("test", (x) -> System.out.println(x));
    }

    // lambda 最终实现形式
    // @FunctionalInterface
    // interface Print<T> {
    // public void print(T x);
    // }
    // public class Lambda {
    // public static void PrintString(String s, Print<String> print) {
    // print.print(s);
    // }
    // private static void lambda$0(String x) {
    // System.out.println(x);
    // }
    // final class $Lambda$1 implements Print{
    // @Override
    // public void print(Object x) {
    // lambda$0((String)x);
    // }
    // }
    // public static void main(String[] args) {
    // PrintString("test", new Lambda().new $Lambda$1());
    // }
    // }
}

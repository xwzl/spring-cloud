package com.spring.base.java;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 参数值传递
 * <p>
 * 方法内部的参数叫形参，调用方法传入的参数是实参
 *
 * @author xuweizhi
 * @since 2019/10/20 10:31
 */
public class ParameterPassingTest {

    /**
     * 测试值传递，值传递的对象都为不可变对象?其实也不是那样吧，比如引用类型传递不可变对象，其内部参数也是可变的
     * <p>
     * 所谓值传递，实际上就是将实际参数值的副本（复制品）传入函数，而参数本身不会受到任何影响。
     * <p>
     * 传入函数的是实际参数值的复制品，不管在函数中对这个复制品如何操作，实际参数值本身不会受到任何影响。
     *
     * @param str 不可变对象
     * @return 返回形参
     */
    @NotNull
    private String stringChange(String str) {
        str += str;
        System.out.println("形参值为:" + str);
        return str;
    }

    /**
     * 引用传递，其实是指地址值传递吧
     *
     * @param list 引用传递
     * @return 可变引用
     */
    @NotNull
    @Contract("_ -> param1")
    private List<String> listChange(@NotNull List<String> list) {
        list.addAll(list);
        System.out.println("形参值为:" + list);
        return list;
    }


    public void swap(int x, int y) {
        int temp = x;
        x = y;
        y = temp;
        System.out.println(String.format("x:%d,y:%d", x, y));
    }

    @Test
    public void swapTest() {
        int x = 1;
        int y = 2;
        swap(x, y);
        System.out.println(String.format("x:%d,y:%d", x, y));
    }

    public static void main(String[] args) {
        ParameterPassingTest parameterPassing = new ParameterPassingTest();
        String immutableVariable = "hello";
        System.out.println("----值传递----");
        parameterPassing.stringChange(immutableVariable);
        System.out.println("实参值为：" + immutableVariable);
        System.out.println("----引用传递----");
        List<String> list = new ArrayList<>();
        list.add("hello");
        parameterPassing.listChange(list);
        System.out.println("实参值为：" + list);
    }
}

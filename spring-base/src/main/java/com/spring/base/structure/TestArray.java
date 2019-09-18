package com.spring.base.structure;

/**
 * @author xuweizhi
 * @since 2019/09/18 21:59
 */
public class TestArray {

    public static void main(String[] args) {
        Structure<String> data = new MyArray<>(2);
        data.add("1");
        data.add("2");
        data.add("3");
        data.add("4");
        data.add("5");
        data.add("6");
        data.add("7");
        data.remove(0);
        data.add("8");
        data.add(1, "9");
        data.set(1, "10");
        System.out.println(data.get(0));
    }
}


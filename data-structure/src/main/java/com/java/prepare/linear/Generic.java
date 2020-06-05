package com.java.prepare.linear;

/**
 * 泛型测试
 *
 * @author xuweizhi
 * @since 2020/05/25 14:15
 */
public class Generic<T> {

    public <T> T getT(T t) {
        return t;
    }

    public static void main(String[] args) {
        Generic<String> genericString = new Generic<>();
        genericString.getT("泛型");

        Generic<Integer> genericInteger = new Generic<>();
        genericInteger.getT("泛型");
    }


}



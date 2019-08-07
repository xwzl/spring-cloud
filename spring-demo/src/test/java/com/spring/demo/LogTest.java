package com.spring.demo;

/**
 * @author xuweizhi
 * @since 2019-08-07
 */
public class LogTest {


    public static void main(String[] args) {
        DemoException demoException = new DemoException();
        Integer integer = demoException.testOne();
        System.out.println(integer);
    }


}

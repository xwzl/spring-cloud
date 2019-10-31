package com.spring.base.java;

import java.util.Scanner;

/**
 * Java assert 断言测试
 *
 * @author xuweizhi
 * @since 2019/10/30 17:25
 */
public class ProcessorAssert {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String next = scanner.next();
            assert !next.equals("Java");
            System.out.println(next);
        }
    }
}

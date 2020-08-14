package com.java.prepare.container;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 双端队列
 *
 * @author xuweizhi
 * @since 2020/08/14 17:45
 */
public class ArrayDoubleQueueTest {

    @Test
    public void arrayQueueTest() {
        Deque<String> deque = new ArrayDeque<>(1000);
        int start = 10;
        for (int i = 0; i < 10; i++) {
            int temp = start;
            // java8 找出 2 的次方
            temp |= (temp >>> 1);
            System.out.println(temp);
            temp |= (temp >>> 2);
            System.out.println(temp);
            temp |= (temp >>> 4);
            System.out.println(temp);
            temp |= (temp >>> 8);
            System.out.println(temp);
            temp |= (temp >>> 16);
            System.out.println(++temp);
            start += 100;
            System.out.println("====================");
        }
    }
}

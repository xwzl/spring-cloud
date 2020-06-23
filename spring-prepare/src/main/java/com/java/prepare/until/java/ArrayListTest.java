package com.java.prepare.until.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * List 源码之 Array List
 *
 * @author xuweizhi
 * @since 2020/06/12 10:06
 */
public class ArrayListTest {

    static List<Integer> list = new ArrayList<Integer>();

    static {
        for (int i = 1; i <= 100000000; i++) {
            list.add(i);
        }
    }

    public static long arrayFor() {
        //开始时间
        long startTime = System.currentTimeMillis();
        for (int j = 0; j < list.size(); j++) {
            Object num = list.get(j);
        }
        //结束时间
        long endTime = System.currentTimeMillis();
        //返回所用时间
        return endTime - startTime;
    }

    public static long arrayIterator() {
        long startTime = System.currentTimeMillis();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static void main(String[] args) {
        long time1 = arrayFor();
        long time2 = arrayIterator();

        System.out.println("ArrayList for循环所用时间==" + time1);
        System.out.println("ArrayList 迭代器所用时间==" + time2);
    }


}

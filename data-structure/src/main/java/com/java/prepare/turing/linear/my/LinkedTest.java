package com.java.prepare.turing.linear.my;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @author xuweizhi
 * @since 2020/07/24 1:35
 */
public class LinkedTest {
    @Test
    public void test() {
        MyLinked<Integer> linkedList = new MyLinked<>();
        linkedList.add(11);
        //linkedList.add(21);
        //linkedList.add(31);
        //linkedList.add(41);
        //for (int i = 0; i < 3; i++) {
        linkedList.remove(0);
        System.out.println(linkedList);
        //}
        //System.out.println(linkedList);
        //linkedList.add(1, 22);

    }

    @Test
    public void testMy() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(11);
        linkedList.add(21);
        linkedList.add(31);
        linkedList.add(41);
        linkedList.remove(1);
        System.out.println(linkedList);
        linkedList.add(1, 22);
        System.out.println(linkedList);
    }
}

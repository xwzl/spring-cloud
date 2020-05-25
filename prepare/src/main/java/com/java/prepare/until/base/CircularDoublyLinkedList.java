package com.java.prepare.until.base;

import org.junit.Test;

/**
 * 循环双链表
 *
 * @author xuweizhi
 * @since 2020/05/14 21:37
 */
public class CircularDoublyLinkedList {

    static class Node {
        Node prior;
        int data;
        Node next;
    }

    void init(int n) {
        // 另外见一个对象的原因是 h 在循环中 h = t 不让数据丢失，真正的数据存放在 node 指向的内存中
        Node node = new Node();
        node.prior = null;
        node.next = null;

        Node h = node;
        Node t = null;


        for (int i = 0; i < n; i++) {
            t = new Node();
            // 拉丁方针
            //t.data = (char) (65 + i);
            t.data = i + 1;
            t.prior = h;
            //t.next = h.next;
            h.next = t;
            // h 每次指向最后一个节点，俗称尾插法
            h = t;
        }

        h.next = node.next;
        node.next.prior = h;
        //t = create(h, n);

        // 拉丁方针： n = 26 写死
        for (int i = 0; i < n; i++) {
            t = create(h, i);
            for (int j = 0; j < n; j++) {
                t = t.next;
                System.out.print(String.format("%4d", t.data) );
            }
            System.out.println();
        }
        System.out.println();
    }

    Node create(Node h, int i) {
        if (i > 0) {
            for (int j = 0; j < i; j++) {
                h = h.next;
            }
        }
        if (i < 0) {
            for (int j = 0; j < -i; j++) {
                h = h.prior;
            }
        }
        return h;
    }

    public static void main(String[] args) {
        CircularDoublyLinkedList c = new CircularDoublyLinkedList();
        c.init(10);
    }

    @Test
    public void test() {
        int n = 10;
        for (int i = 0; i < n; i++) {
            int temp = i + 1;
            for (int j = 0; j < n; j++) {
                if (temp == (n + 1)) {
                    temp = 1;
                }
                System.out.print(temp);
                temp++;
            }
            System.out.println();
        }
    }


}

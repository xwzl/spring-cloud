package com.java.prepare.turing.linear.my;

import org.junit.Test;

/**
 * 循环链表
 *
 * @author xuweizhi
 * @since 2020/07/24 1:53
 */
public class CircularLinkedList {

    protected Node first;

    private int size;

    private static class Node {
        private int data;
        private Node next;

        public Node() {
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public void add(int data) {
        Node node = new Node(data, null);
        if (first == null) {
            this.first = node;
            first.next = first;
        } else {
            Node temp = first;
            while (temp.next != first) {
                temp = temp.next;
            }
            //temp.next = new Node(data, temp.next);
            temp.next = new Node(data, first);
        }
        size++;
    }


    @Override
    public String toString() {
        Node temp = first;
        if (first == null) {
            return "[]";
        }
        StringBuffer sb = new StringBuffer("[ ");
        if (temp.next == first) {
            return sb.append(temp.data).append(" ]").toString();
        }
        while (temp.next != first) {
            sb.append(temp.data).append(",");
            temp = temp.next;
        }
        sb.append(temp.data);
        sb.append(" ]");
        return sb.toString();
    }

    @Test
    public void test() {
        CircularLinkedList list = new CircularLinkedList();
        for (int i = 1; i < 42; i++) {
            list.add(i);
        }
        int index = 1;
        first = list.first;
        Node pre = null;
        while (list.size > 1) {
            if (index % 3 == 0) {
                pre.next = pre.next.next;
                first = pre.next;
                list.size--;
                index++;
                continue;
            }
            pre = first;
            first = first.next;
            index++;
        }
        System.out.println();
        System.out.println(first.data);
    }

}

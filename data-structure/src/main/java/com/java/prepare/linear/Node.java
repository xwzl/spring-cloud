package com.java.prepare.linear;

import lombok.Data;

/**
 * 单链表实现
 *
 * @author xuweizhi
 * @since 2020/05/12 23:37
 */
@Data
public class Node<T> {

    private T data;

    private Node<T> next;

    /**
     * 头插法
     */
    public static <T> Node<T> initNode(int num, T t) {
        Node<T> head = null;
        Node<T> temp = null;
        int i = 0;
        while (i < num) {
            temp = new Node<>();
            temp.setData(t);
            if (head != null) {
                temp.setNext(head);
            }
            head = temp;
            i++;
        }
        return head;
    }

    /**
     * 尾插法
     */
    public static <T> Node<T> tailNode(int num, T t) {
        Node<T> head = null;
        Node<T> temp = null;
        int i = 0;
        while (i < num) {
            Node<T> node = new Node<>();
            node.setData(t);
            if (head == null) {
                head = temp = node;
            } else {
                temp.setNext(node);
                temp = node;
            }
            i++;
        }
        return head;
    }

    public static void main(String[] args) {
        Node<String> node = tailNode(13, "1");
        System.out.println(node);
    }

}

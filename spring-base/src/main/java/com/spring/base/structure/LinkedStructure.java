package com.spring.base.structure;

import com.spring.base.guava.Student;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

/**
 * 链表实现
 *
 * @author xuweizhi
 * @since 2019/09/19 9:18
 */
@ToString
public class LinkedStructure<T> extends AbstractStructure<T> {

    /**
     * 头结点
     */
    Node head = null;

    /**
     * 链表中的节点，data代表节点的值，next是指向下一个节点的引用
     */
    @ToString
    class Node {

        /**
         * 下个节点
         */
        Node next = null;

        /**
         * 节点内容
         */
        T data;

        public Node(T data) {
            this.data = data;
        }
    }

    /**
     * 1 head -> next = null
     * 2 head -> next = node1 -> next = null
     * 3 head -> next = node1 -> next = node2 > next = null
     */
    @Override
    public boolean add(T t) {

        // 新增的节点
        Node newNode = new Node(t);

        // 初始化头结点
        if (head == null) {
            head = newNode;
            return true;
        }

        // 中间变量
        Node temp = head;

        // 返回最后一个节点
        while (temp.next != null) {
            // 当前节点指向下一个节点
            temp = temp.next;
        }

        temp.next = newNode;
        return true;
    }

    @Override
    public T remove(int index) {
        int temp = 0;
        if (head == null) {
            throw new RuntimeException(String.format("index %d : length %d", index, 0));
        }
        Node tempNode = head;
        while (tempNode.next != null) {
            if (index == 0 || (temp + 1) == index) {
                break;
            }
            tempNode = tempNode.next;
            temp++;
        }
        T data = tempNode.data;
        if (tempNode.next == null) {
            throw new RuntimeException(String.format("index %d : length %d", index, temp+1));
        }
        if (index == 0) {
            head = head.next;
            return data;
        }
        tempNode.next = tempNode.next.next;
        return data;
    }

    public static void main(String[] args) {
        List<String> s = new LinkedList<>();
        Structure<Student> linked = new LinkedStructure<>();
        linked.add(new Student("张三", 12));
        linked.add(new Student("李四", 12));
        linked.add(new Student("王五", 12));
        linked.add(new Student("赵六", 12));
        linked.remove(4);
        System.out.println(linked);
    }
}

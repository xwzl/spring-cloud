package com.java.prepare.turing.tree;


import com.java.prepare.turing.tree.LinkedBinaryTree.BinaryNode;
import org.junit.Test;

/**
 * 队列测试
 *
 * @author xuweizhi
 * @since 2020/07/25 12:28
 */
public class LinkedQueueTest {

    @Test
    public void append() {
        LinkedQueue<Node> queue = new LinkedQueue<>();
        queue.append(new BinaryNode<>("a"));
        queue.append(new BinaryNode<>("b"));
        queue.append(new BinaryNode<>("c"));
        System.out.println(queue.getFirst());
        System.out.println(queue.getFirst());
        System.out.println(queue.getFirst());
        System.out.println(queue.getFirst());

    }
}
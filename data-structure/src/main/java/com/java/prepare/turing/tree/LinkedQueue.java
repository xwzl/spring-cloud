package com.java.prepare.turing.tree;

/**
 * 队列
 *
 * @author xuweizhi
 * @since 2020/07/25 12:19
 */
public class LinkedQueue<E> {


    int size;

    private QueueNode<E> first;

    private QueueNode<E> end;

    public LinkedQueue() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void append(E data) {
        if (data == null) {
            throw new NullPointerException("该节点数据元素不能为空");
        }
        QueueNode<E> node = new QueueNode<>(data);
        if (first == null) {
            end = node;
            first = end;
        } else {
            end.next = node;
            end = node;
        }
        size++;
    }

    public E getFirst() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("索引下标越界");
        }
        E data = first.data;
        first = first.next;
        if (--size == 0) {
            end = null;
            first = null;
        }
        return data;
    }

    private static class QueueNode<E> {
        E data;
        QueueNode<E> next;

        public QueueNode(E data) {
            this.data = data;
        }
    }
}

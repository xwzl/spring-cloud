package com.java.prepare.turing.linear.my;

import lombok.Data;

import java.util.NoSuchElementException;

/**
 * @author xuweizhi
 * @since 2020/07/23 23:29
 */
@Data
public class MyLinked<E> {

    private Node<E> first;

    private int size;

    private static class Node<E> {
        private E data;
        private Node<E> next;

        public Node() {
        }

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    public void add(E e) {
        Node<E> next = new Node<>(e, null);
        if (first == null) {
            first = next;
        } else {
            Node<E> temp = first;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = next;
        }
        size++;
    }

    public void add(int index, E e) {
        checkIndex(index);
        if (first == null) {
            first = new Node<>(e, null);
            size++;
            return;
        }
        if (index == 0) {
            first = new Node<>(e, first);
        } else {
            Node<E> temp = first;
            // index = 1,
            for (int i = 1; i < index; i++) {
                temp = temp.next;
            }
            temp.next = new Node<>(e, temp.next);
        }
        size++;
    }

    public E remove() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        E data = first.data;
        first = first.next;
        size--;
        return data;
    }

    public int size() {
        return size;
    }

    public E remove(int index) {
        checkRemoveIndex(index);
        E data = null;
        if (index == 0) {
            return remove();
        }
        Node<E> temp = first;
        for (int i = 1; i < index; i++) {
            temp = temp.next;
        }
        data = temp.data;
        System.out.println(data);
        temp.next = temp.next.next;
        size--;
        return data;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
    }

    private void checkRemoveIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
    }

    @Override
    public String toString() {
        if (size <= 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> temp = first;
        while (temp != null) {
            sb.append(temp.data);
            temp = temp.next;
            if (temp != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }


}

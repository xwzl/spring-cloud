package com.java.prepare.turing.linear.my;

import java.util.NoSuchElementException;

/**
 * 线性表的链式储存结构的简单 单链表实现
 */
public class MySingleLinkedList<E> {

    /**
     * 空构造器,内部的节点均没有初始化,在第一次添加时才会初始化。
     */
    public MySingleLinkedList() {
    }

    /**
     * 元素个数
     */
    private int size;

    /**
     * 指向头结点的引用
     */
    private Node<E> first;

    /**
     * 单链表内部的节点
     */
    private static class Node<E> {
        //下一个结点的引用
        Node<E> next;
        //结点数据
        E data;

        //节点构造器
        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * 默认 添加元素到单链表尾部
     */
    public void add(E e) {
        //创建新节点
        Node<E> newNode = new Node<>(e, null);
        //如果头结点不为空
        if (first != null) {
            /*寻找尾节点*/
            Node f = first;
            while (f.next != null) {
                f = f.next;
            }
            f.next = newNode;
        } else {
            //如果头结点为空,那么新节点就是第一个节点,就是头结点。
            first = newNode;
        }
        size++;
    }

    /**
     * 默认 删除单链表头部元素
     */
    public E remove() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        //如果头结点不为空
        E e = first.data;
        first = first.next;
        size--;
        return e;
    }


    /**
     * 添加元素到单链表指定索引位置，原索引节点链接为next
     */
    public void add(E e, int index) {
        checkPositionIndex(index);
        //如果index等于0
        if (index == 0) {
            first = new Node<>(e, first);
            size++;
            return;
        }
        //如果index等于1
        if (index == 1) {
            first.next = new Node<>(e, first.next);
            size++;
            return;
        }
        if (index == size) {
            add(e);
            return;
        }
        //暂时是头结点,循环结束后指向index索引节点
        Node<E> x = first;
        //index索引的前一个节点
        Node<E> y = null;
        for (int i = 0; i < index; i++) {
            x = x.next;
            if (i == index - 2) {
                y = x;
            }
        }
        //原index索引的前驱节点的next指向新节点,新节点的next节点指向原index索引节点
        y.next = new Node<>(e, x);
        size++;
    }


    /**
     * 删除单链表指定索引位置的元素
     */
    public E remove(int index) {
        checkElementIndex(index);
        if (index == 0) {
            return remove();
        }
        //如果index等于1
        if (index == 1) {
            E e = first.next.data;
            first.next = first.next.next;
            size--;
            return e;
        }
        //暂时是头结点,循环结束后指向index索引的节点的next节点
        Node<E> x = first;
        //index索引的前一个节点
        Node<E> y = null;
        //index索引的节点
        Node<E> z = null;
        for (int i = 0; i <= index; i++) {
            x = x.next;
            if (i == index - 2) {
                y = x;
            }
            if (i == index - 1) {
                z = x;
            }
        }
        y.next = x;
        E e = z.data;
        size--;
        z = null;
        return e;
    }

    /**
     * 获取元素数量
     */
    public int size() {
        return size;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (size > 0) {
            Node<E> f = first;
            stringBuilder.append("[ ");
            for (int i = 0; i < size; i++) {
                stringBuilder.append(f.data.toString());
                if (i != size - 1) {
                    stringBuilder.append(" , ");
                }
                f = f.next;
            }
            stringBuilder.append(" ]");
            return stringBuilder.toString();
        }
        return "[]";
    }


    /**
     * 检查索引是否越界，用在删除、获取
     */
    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
    }


    /**
     * 检查索引是否越界，用在添加
     */
    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
    }
}


package com.java.prepare.queue;

/**
 * 链式队列其实就是特殊的单链表，只不过它只能尾进头出而已。
 *
 * @author xuweizhi
 * @since 2020/05/27 23:42
 */
public class Node {

    Object element; //数据域
    Node next;  //指针域

    //头结点的构造方法
    public Node(Node nextVal) {
        this.next = nextVal;
    }

    //非头结点的构造方法
    public Node(Object obj, Node nextVal) {
        this.element = obj;
        this.next = nextVal;
    }

    //获得当前结点的后继结点
    public Node getNext() {
        return this.next;
    }

    //获得当前的数据域的值
    public Object getElement() {
        return this.element;
    }

    //设置当前结点的指针域
    public void setNext(Node nextVal) {
        this.next = nextVal;
    }

    //设置当前结点的数据域
    public void setElement(Object obj) {
        this.element = obj;
    }

    public String toString() {
        return this.element.toString();
    }
}

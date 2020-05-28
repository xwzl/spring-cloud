package com.java.prepare.until.base.queue;   //优先级队列元素类

/**
 * 设计顺序优先级队列分为两个类：
 * <p>
 * 数据元素类
 * <p>
 * 优先级队列类
 *
 * @author xuweizhi
 * @since 2020/05/26 21:28
 */
public class Element {

    private Object element; // 数据
    private int priority; // 优先级

    public Element(Object obj, int priority) {
        this.element = obj;
        this.priority = priority;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

}
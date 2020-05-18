package com.java.prepare.base;

/**
 * 栈的 Java 实现
 *
 * @author xuweizhi
 * @since 2020/05/18 22:43
 */
public class StackJava<T> {

    private int top = -1;

    private T[] elements;

    private int size = 10;

    public StackJava() {
        elements = (T[]) new Object[size];
    }

    public int push(T t) {
        if (top >= size -1) {
            size = (int)
                    (size * 1.5);
            T[] news = (T[]) new Object[size];
            System.arraycopy(elements, 0, news, 0, elements.length);
            elements=news;

        }
        top++;
        elements[top] = t;
        return top;
    }

    public T pop() {
        if (top <= -1) {
            System.out.println("出错了");
        }
        return elements[top--];
    }

    public static void main(String[] args) {
        StackJava<Integer> s = new StackJava<>();
        for (int i = 0; i < 100; i++) {
            s.push(i);
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(s.pop());
        }
    }
}

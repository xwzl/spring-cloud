package com.java.prepare.until.base.queue;

/**
 * 编写一个判断一个字符串是否是回文的算法。
 *
 * 设字符数组str中存放了要判断的字符串。把字符数组中的字符逐个分别存入一个队列和栈中，然后逐个出队和出栈比较出队的字符
 * 与出栈的字符是否相同，若全部相等则该字符串为回文。
 *
 * @author xuweizhi
 * @since 2020/05/27 23:42
 */
public interface Stack {

    //入栈
    void push(Object obj) throws Exception;

    //出栈
    Object pop() throws Exception;

    //获得栈顶元素
    Object getTop() throws Exception;

    //判断栈是否为空
    boolean isEmpty();
}
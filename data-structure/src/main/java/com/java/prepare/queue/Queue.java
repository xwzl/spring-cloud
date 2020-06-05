package com.java.prepare.queue;

/**
 * 队列接口
 *
 * @author xuweizhi
 * @since 2020/05/27 23:35
 */
public interface Queue {
    //入队
    void append(Object obj) throws Exception;

    //出队
    Object delete() throws Exception;

    //获得队头元素
    Object getFront() throws Exception;

    //判断对列是否为空
    boolean isEmpty();
}

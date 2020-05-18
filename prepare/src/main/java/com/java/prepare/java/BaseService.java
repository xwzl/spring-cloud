package com.java.prepare.java;

/**
 * @author xuweizhi
 * @since 2020/05/15 22:21
 */
public interface BaseService<T> {

    void run(T t);

    T getT(T t);
}

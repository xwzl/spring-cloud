package com.spring.base.java.generic;

/**
 * 泛型接口
 *
 * @author xuweizhi
 * @since 2019/12/31 14:13
 */
public interface GenericInterface<E, K> {

    /**
     * 获取 k
     *
     * @param e e
     * @param k k
     * @return k
     */
    K getK(E e, K k);
}

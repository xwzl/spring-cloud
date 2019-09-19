package com.spring.base.structure;

/**
 * 自定义集合顶级接口
 *
 * @author xuweizhi
 * @since 2019/09/18 21:57
 */
public interface Structure<T> {

    /**
     * 根据传入的下标，返回下标对应的值
     *
     * @param index 下标
     * @return 数组元素
     */
    T get(int index);

    /**
     * 添加数据元素
     *
     * @param t 数据元素
     * @return 返回
     */
    boolean add(T t);

    /**
     * 添加指定位置的下标元素
     *
     * @param index 下标
     * @param t     数据元素
     */
    void add(int index, T t);

    /**
     * 添加指定位置的下标元素
     *
     * @param index 下标
     * @param t     数据元素
     */
    void set(int index, T t);

    /**
     * 移除数组中的元素
     *
     * @param index 下标
     * @return 移除成功标志
     */
    T remove(int index);


    /**
     * 集合长度
     *
     * @return 返回值
     */
    int size();

    /**
     * 清除元素
     *
     * @return 清除标志
     */
    boolean clear();

    /**
     * 判断集合是否为空
     *
     * @return true
     */
    boolean isEmpty();
}

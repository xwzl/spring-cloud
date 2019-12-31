package com.spring.base.java.generic;

import java.lang.reflect.ParameterizedType;

/**
 * 泛型接口
 *
 * @author xuweizhi
 * @since 2019/12/31 14:13
 */
public abstract class BaseDao<T> {

    /**
     * 哪个子类调的这个方法，得到的class就是子类处理的类型（非常重要）
     */
    public BaseDao() {
        Class clazz = this.getClass();  //拿到的是子类
        ParameterizedType pt = (ParameterizedType) clazz.getGenericSuperclass();  //BaseDao<Category>
        clazz = (Class) pt.getActualTypeArguments()[0];
        System.out.println(clazz);

    }


}

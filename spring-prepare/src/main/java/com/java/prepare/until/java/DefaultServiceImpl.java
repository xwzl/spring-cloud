package com.java.prepare.until.java;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author xuweizhi
 * @since 2020/05/15 22:22
 */
public abstract class DefaultServiceImpl<T> implements BaseService<T> {

    private Class<T> clazz;

    @Resource
    private Qqq qqq;

    public DefaultServiceImpl() {
        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType type1 = (ParameterizedType) type;
        Type[] actualTypeArguments = type1.getActualTypeArguments();
        Type actualTypeArgument = actualTypeArguments[0];
        if (actualTypeArgument != null) {
            clazz = (Class<T>) actualTypeArgument;
        }
    }

    @Override
    public void run(T t) {
        System.out.println(t);
    }

    @Override
    public T getT(T t) {
        return t;
    }
}

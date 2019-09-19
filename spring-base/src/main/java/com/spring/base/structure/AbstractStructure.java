package com.spring.base.structure;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xuweizhi
 * @since 2019/09/18 22:05
 */
@Data
public abstract class AbstractStructure<T> implements Structure<T>, Serializable {

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public void add(int index, T t) {
    }

    @Override
    public void set(int index, T t) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean clear() {
        return false;
    }
}

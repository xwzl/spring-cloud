package com.spring.base.structure;

/**
 * @author xuweizhi
 * @since 2019/09/18 22:47
 */
public class MyArray<T> extends ArrayStructure<T> {

    public MyArray() {
        super();
    }

    public MyArray(int capacity) {
        super(capacity);
    }

    @Override
    public T get(int index) {
        return super.get(index);
    }

    @Override
    public boolean add(T t) {
        return super.add(t);
    }

    @Override
    public void add(int index, T t) {
        super.add(index, t);
    }

    @Override
    public void set(int index, T t) {
        super.set(index, t);
    }

    @Override
    public T remove(int index) {
        return super.remove(index);
    }

    @Override
    public int size() {
        return super.size();
    }
}

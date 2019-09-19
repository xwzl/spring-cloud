package com.spring.base.structure;

import org.jetbrains.annotations.Contract;

/**
 * 数组实现
 *
 * @author xuweizhi
 * @since 2019/09/18 22:47
 */
public class ArrayStructure<T> extends AbstractStructure<T> {

    /**
     * 真正的数据实体
     */
    private Object[] elementData;

    /**
     *
     */
    private int capacity;

    /**
     * 数组元素个数
     */
    private int size;

    @Contract(pure = true)
    public ArrayStructure() {
        elementData = new Object[15];
        this.size = 0;
        this.capacity = 15;
    }

    public ArrayStructure(int capacity) {
        elementData = new Object[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException(String.format("index %d out of bounds for length %d", index, size));
        }
        return (T) this.elementData[index];
    }

    @Override
    public boolean add(T t) {
        try {
            if (size >= capacity) {
                extensionArray();
            }
            this.elementData[size] = t;
            this.size += 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void add(int index, T t) {
        crossing(index, t);
    }

    @Override
    public void set(int index, T t) {
        crossing(index, t);
    }

    private void crossing(int index, T t) {
        if (index > size || index == capacity) {
            throw new RuntimeException(String.format("index %d : length %d", index, size));
        }
        if (size + 1 - index >= 0) {
            System.arraycopy(this.elementData, index - 1, this.elementData, index, size + 1 - index);
        }
        //for (int i = size; i >= index; i--) {
        //    this.elementData[i] = this.elementData[i - 1];
        //}
        this.size += 1;
        this.elementData[index] = t;
    }

    @Override
    public T remove(int index) {
        if (size == 0 | index > size) {
            throw new RuntimeException(String.format("index %d : length %d", 0, size));
        }
        T remove = (T) this.elementData[index];
        System.arraycopy(this.elementData, index + 1, this.elementData, index, size - index);
        //for (int i = index; i < size; i++) {
        //    this.elementData[i] = this.elementData[i + 1];
        //}
        this.size -= 1;
        return remove;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean clear() {
        this.size = 0;
        this.capacity = 15;
        this.elementData = new Object[capacity];
        return true;
    }

    /**
     * 扩容
     */
    private void extensionArray() {
        this.capacity = (int) (capacity * 1.5);
        Object[] tempElement = new Object[capacity];
        System.arraycopy(this.elementData, 0, tempElement, 0, size);
        this.elementData = tempElement;
    }


}

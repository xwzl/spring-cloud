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
     * 当前集合容量
     */
    private int capacity;

    /**
     * 初始化数值
     */
    private final static int INIT_CAPACITY = 15;

    /**
     * 初始化大小
     */
    private final static int INIT_SIZE = 0;

    /**
     * 数组元素个数
     */
    private int size;

    @Contract(pure = true)
    public ArrayStructure() {
        elementData = new Object[INIT_CAPACITY];
        this.size = INIT_SIZE;
        this.capacity = INIT_CAPACITY;
    }

    /**
     * 不考虑负值的情况
     */
    public ArrayStructure(int capacity) {
        elementData = new Object[capacity];
        this.size = INIT_SIZE;
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
            this.size++;
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
        if (size == 0 || index < 0) {
            throw new RuntimeException(String.format("Index %d out of bounds for length  %d", index, size));
        }
        if (index > size || index == capacity) {
            throw new RuntimeException(String.format("Index %d out of bounds for length  %d", index, size));
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
        this.size = INIT_SIZE;
        this.capacity = INIT_CAPACITY;
        this.elementData = new Object[capacity];
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 扩容
     */
    private void extensionArray() {
        this.capacity = (int) (capacity * 1.5);
        Object[] tempElement = new Object[capacity];
        System.arraycopy(this.elementData, INIT_SIZE, tempElement, INIT_SIZE, size);
        this.elementData = tempElement;
    }

}

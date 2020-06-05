package com.java.prepare.search.tree;

import java.util.NoSuchElementException;

/**
 * 二叉树的顺序存储结构的简单实现
 */
public class ArrayBinaryTree<E> {

    /**
     * 深度
     */
    private int deep;
    /**
     * 容量,也是节点数量
     */
    private int capacity;
    /**
     * 底层数组
     */
    private Object[] elements;

    /**
     * 节点真正数量
     */
    private int size;


    /**
     * 指定树的深度，初始化数组
     *
     * @param deep 树深度
     */
    public ArrayBinaryTree(int deep) {
        this.deep = deep;
        this.elements = new Object[capacity = (int) Math.pow(2, deep) - 1];
    }

    /**
     * 指定树的深度和根节点
     *
     * @param deep
     * @param root
     */
    public ArrayBinaryTree(int deep, E root) {
        this(deep);
        addRoot(root);
    }


    /**
     * 添加根节点
     *
     * @param root 根节点数据
     */
    public void addRoot(E root) {
        checkNullData(root);
        elements[0] = root;
        size++;
    }


    /**
     *
     * 添加子节点
     *
     * @param parentIndex 父节点索引
     * @param data        节点数据
     * @param left        是否是左子节点,true 是;false 否
     * @return 添加成功后子节点的索引
     */
    public int addChild(int parentIndex, E data, boolean left) {
        checkParentIndex(parentIndex);
        checkNullData(data);
        int childIndex;
        if (left) {
            childIndex = parentIndex * 2 + 1;
        } else {
            childIndex = parentIndex * 2 + 2;
        }
        addChild(childIndex, data);
        size++;
        return childIndex;
    }

    /**
     * 添加子节点
     *
     * @param childIndex 子节点索引
     * @param data       子节点数据
     */
    private void addChild(int childIndex, E data) {
        if (elements[childIndex] != null) {
            throw new IllegalStateException("该父节点已经存在该子节点");
        }
        elements[childIndex] = data;
    }


    /**
     * 是否是空树
     *
     * @return true 是 ;false 否
     */
    public boolean isEmpty() {
        return elements[0] == null;
    }


    /**
     * 返回节点数
     *
     * @return 节点数
     */
    public int size() {
        return size;
    }


    /**
     * 获取索引为index的节点的父节点
     *
     * @param index 索引
     * @return 父节点数据
     */
    public E getParent(int index) {
        if (index == 0) {
            return null;
        }
        return (E) elements[(index - 1) / 2];
    }

    /**
     * 获取索引为index的节点的右子节点
     *
     * @param index 索引
     * @return 右子节点数据
     */
    public E getRight(int index) {
        if (2 * index + 1 >= capacity) {
            return null;
        }
        return (E) elements[index * 2 + 2];
    }

    /**
     * 获取索引为index的节点的左子节点
     *
     * @param index 索引
     * @return 左子节点
     */
    public E getLeft(int index) {
        if (2 * index + 1 >= capacity) {
            return null;
        }
        return (E) elements[2 * index + 1];
    }


    /**
     * 获取根节点
     *
     * @return 根节点数据
     */
    public E getRoot() {
        return (E) elements[0];
    }

    /**
     * 获取节点出现的首个索引位置
     *
     * @param data 节点数据
     * @return 节点索引, 或者-1--不存在该节点
     */
    public int indexOf(E data) {
        for (int i = 0; i < capacity; i++) {
            if (elements[i].equals(data)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 检查子节点是否已经存在
     *
     * @param message 消息
     */
    private void checkChild(int childIndex, String message) {
        if (elements[childIndex] == null) {
            throw new IllegalStateException(message);
        }
    }


    /**
     * 数据判null
     *
     * @param data 添加的数据
     */
    private void checkNullData(E data) {
        if (data == null) {
            throw new NullPointerException("数据不允许为null");
        }
    }

    /**
     * 检查父节点是否存在
     *
     * @param parentIndex 父节点索引
     */
    private void checkParentIndex(int parentIndex) {
        if (elements[parentIndex] == null) {
            throw new NoSuchElementException("父节点不存在");
        }
    }
}


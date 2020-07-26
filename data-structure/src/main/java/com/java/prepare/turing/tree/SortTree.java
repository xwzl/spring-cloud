package com.java.prepare.turing.tree;

import com.java.prepare.turing.tree.AbstractBinaryTree.BinaryNode;

/**
 * @author xuweizhi
 * @since 2020/07/26 12:40
 */
public interface SortTree<E> {

    /**
     * 查找,开放给外部使用的api
     */
    boolean contains(E e);

    /**
     * 插入,内部调用的方法,先从根节点开始递归查找要插入的位置,然后插入
     *
     * @param e 要插入的数据
     */
    void insert(E e);

    /**
     * 查找最小的节点
     *
     * @param root 根节点
     * @return 最小的节点
     */
    BinaryNode<E> findMin(BinaryNode<E> root);

    /**
     * 查找最大的节点
     *
     * @param root 根节点
     * @return 最大的节点
     */
    BinaryNode<E> findMax(BinaryNode<E> root);

    /**
     * 删除,开放给外部使用的api
     *
     * @param e 要删除的元素
     */
    void remove(E e);
}

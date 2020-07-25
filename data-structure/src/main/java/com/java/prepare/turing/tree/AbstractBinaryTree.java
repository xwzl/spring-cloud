package com.java.prepare.turing.tree;

import com.java.prepare.turing.tree.LinkedBinaryTree.BinaryNode;

import java.util.NoSuchElementException;

/**
 * @author xuweizhi
 * @since 2020/07/25 13:36
 */
public abstract class AbstractBinaryTree<E> implements BinaryTree<E> {

    protected int size;

    protected Node<E> root;

    @Override
    public Node<E> getRoot() {
        return root;
    }

    public abstract Node<E> addChild(Node<E> parent, E data, boolean left);

    public Node<E> getLeft(Node<E> parent) {
        return parent == null ? null : ((BinaryNode<E>) parent).left;
    }

    public Node<E> getRight(Node<E> parent) {
        return parent == null ? null : ((BinaryNode<E>) parent).right;
    }

    public void checkNullData(E data) {
        if (data == null) {
            throw new NullPointerException("该节点数据不能为空");
        }
    }

    public void checkNullParent(Node<E> parent) {
        if (parent == null) {
            throw new NoSuchElementException("父节点不能为空");
        }
        checkNodeType(parent);
    }

    /**
     * 判断当前节点类型
     *
     * @param parent 父节点
     */
    public abstract void checkNodeType(Node<E> parent);

    /**
     * 是否为空树
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回节点数
     *
     * @return 节点数
     */
    public int size() {
        return size;
    }
}

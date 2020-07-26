package com.java.prepare.turing.tree;

import java.util.NoSuchElementException;

/**
 * 二叉树的链式存储结构的简单实现
 *
 * @author xuweizhi
 * @since 2020/07/25 11:48
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> implements BinaryTree<E> {

    public LinkedBinaryTree() {
    }

    public LinkedBinaryTree(E root) {
        checkNullData(root);
        this.root = new BinaryNode<>(root);
        size++;
    }

    @Override
    public Node<E> addChild(Node<E> parent, E data, boolean left) {
        checkNullParent(parent);
        checkNullData(data);
        BinaryNode<E> node = new BinaryNode<>(data);
        BinaryNode<E> parentNode = ((BinaryNode<E>) parent);
        if (left) {
            if (((BinaryNode<E>) parent).left != null) {
                throw new IllegalMonitorStateException("该父节点已经存在左子节点,添加失败");
            }
            parentNode.left = node;
        } else {
            if (parentNode.right != null) {
                throw new IllegalMonitorStateException("该父节点已经存在右子节点,添加失败");
            }
            parentNode.right = node;
        }
        size++;
        return node;
    }

    @Override
    public void checkNullData(E data) {
        if (data == null) {
            throw new NullPointerException("该节点数据不能为空");
        }
    }

    @Override
    public void checkNullParent(Node<E> parent) {
        if (parent == null) {
            throw new NoSuchElementException("父节点不能为空");
        }
        checkNodeType(parent);
    }


    @Override
    public void checkNodeType(Node<E> parent) {
        if (!(parent instanceof BinaryNode)) {
            throw new ClassCastException("当前节点不是二叉树结点");
        }
    }


}

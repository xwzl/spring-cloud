package com.java.prepare.turing.tree;

import com.java.prepare.turing.tree.LinkedBinaryTree.BinaryNode;

/**
 * Thread 线索二叉树
 * <p>
 * 我们把这种指向前驱和后继的引用称为线索，加上线索的二叉链表称为线索链表，相应的二叉树就称为线索二叉树（Threaded Binary Tree）。
 * <p>
 * 对二叉树以某种遍历方式（如先序、中序、后序或层序）进行遍历，使其变为线索二叉树的过程称为对二叉树进行线索化。 根据线索性质的不同，
 * 线索二叉树可分为前序线索二叉树、中序线索二叉树和后序线索二叉树三种
 *
 * @author xuweizhi
 * @since 2020/07/25 13:11
 */
public class ThreadBinaryTree<E> extends AbstractBinaryTree<E> implements BinaryTree<E> {

    /**
     * 线索化的时候保存刚刚访问过的前驱节点
     */
    private ThreadNode<E> pre;

    @Override
    public ThreadNode<E> getRoot() {
        return (ThreadNode<E>) root;
    }

    public ThreadBinaryTree() {
    }

    public ThreadBinaryTree(E data) {
        checkNullData(data);
        this.root = new ThreadNode<>(data);
        size++;
    }

    @Override
    public ThreadNode<E> addChild(Node<E> parent, E data, boolean left) {
        checkNullParent(parent);
        checkNullData(data);
        ThreadNode<E> node = new ThreadNode<>(data);
        ThreadNode<E> parentNode = (ThreadNode<E>) parent;
        if (left) {
            if (parentNode.left != null) {
                throw new IllegalMonitorStateException("该父节点的左子节点已存在");
            }
            parentNode.lTag = false;
            parentNode.left = node;
        } else {
            if (parentNode.right != null) {
                throw new IllegalMonitorStateException("该父节点的左子节点已存在");
            }
            // 设置为非线索结点
            parentNode.rTag = false;
            parentNode.right = node;
        }
        return node;
    }

    @Override
    public ThreadNode<E> getLeft(Node<E> parent) {
        return (ThreadNode<E>) super.getLeft(parent);
    }

    @Override
    public ThreadNode<E> getRight(Node<E> parent) {
        return (ThreadNode<E>) super.getRight(parent);
    }

    @Override
    public void checkNodeType(Node<E> parent) {
        if (!(parent instanceof ThreadNode)) {
            throw new ClassCastException("该节点不是线索二叉树节点");
        }
    }

    /**
     * 将以root为根节点的二叉树线索化  中序法
     *
     * @return true 线索化成功 ;false 线索化失败
     */
    public boolean inThread() {
        if (isEmpty()) {
            return false;
        }
        inThread(getRoot());
        return true;
    }


    /**
     * 将以root为根节点的二叉树线索化  中序法
     *
     * @param root 节点,从根节点开始,为当前结点
     */
    private void inThread(ThreadNode<E> root) {
        ThreadNode<E> left = getLeft(root);
        if (left != null) {
            //如果左子节点不为null,则继续递归遍历该左子节点
            inThread(left);
        } else {
            // 如果左子节点为null，因为其前驱结点刚刚访问过，将左子节点设置为线索
            // 完成前驱结点的线索化
            root.left = pre;
            root.lTag = true;
        }
        //如果前驱没有右子节点，那就把当前节点当作前驱结点的后继节点
        if (pre != null && null == pre.right) {
            pre.rTag = true;
            pre.right = root;
        }
        //每次将当前节点设置为pre，下一个节点就把该节点当成前驱结点
        pre = root;

           ThreadNode<E> right = getRight(root);
        if (right != null) {
            //如果右子节点不为null,则继续递归遍历该右子节点
            inThread(right);
        }
    }

    /**
     * 中序遍历线索二叉树
     */
    public void inThreadList(ThreadNode<E> root) {
        if (root == null) {
            return;
        }
        //查找中序遍历的起始节点
        while (root != null && !root.lTag) {
            root = (ThreadNode<E>) root.left;
        }
        while (root != null) {
            System.out.print(root.data + ",");
            // 如果右子节点是线索
            if (root.rTag) {
                root = (ThreadNode<E>) root.right;
            } else {
                //有右子节点，遍历右子节点
                root = (ThreadNode<E>) root.right;
                //如果右子节点不为null，并且右子节点的左子结点存在
                while (root != null && !root.lTag) {
                    root = (ThreadNode<E>) root.left;
                }
            }
        }
    }


    static class ThreadNode<E> extends BinaryNode<E> implements Node<E> {

        /**
         * false：指向左子节点、true：前驱线索
         */
        boolean lTag;

        /**
         * false：指向右子节点、true：后继线索
         */
        boolean rTag;

        public ThreadNode(E data) {
            super(data);
        }
    }


}

package com.java.prepare.tree;

import lombok.Data;

/**
 * 线索二叉树
 * <p>
 * 按照某种方式对二叉树进行遍历，可以把二叉树中所有结点排序为一个线性序列，在该序列中，除第一个结点外每个结点有且仅有一个直接
 * 前驱结点；除最后一个结点外每一个结点有且仅有一个直接后继结点；
 * <p>
 * 在有N个节点的二叉树中需要利用N+1个空指针添加线索，这是因为在N个节点的二叉树中，每个节点有2个指针，所以一共有2N个指针，除了
 * 根节点以外，每一个节点都有一个指针从它的父节点指向它，所以一共使用了N-1个指针，所以剩下2N-(N-1)也就是N+1个空指针；
 * <p>
 * 若能利用这些空指针域来存放指向该节点的直接前驱或是直接后继的指针，则可由此信息直接找到在该遍历次序下的前驱结点或后继结点，从
 * 而比递归遍历提高了遍历速度，节省了建立系统栈所使用的存储空间；
 *
 * @author xuweizhi
 * @since 2020/05/30 22:41
 */
@Data
public class ThreadTree {

    private Node root;         // 根节点
    private int size;          // 大小
    private Node pre = null;   // 线索化的时候保存前驱

    public ThreadTree() {
        this.root = null;
        this.size = 0;
        this.pre = null;
    }

    public ThreadTree(int[] data) {
        this.pre = null;
        this.size = data.length;
        this.root = createTree(data, 1);   // 创建二叉树
    }

    /**
     * 创建二叉树
     * <p>
     * 得出的二叉树为
     * <p>
     * ------------1-----------
     * --------2-------3-------
     * -----4----5---6----7----
     */
    public Node createTree(int[] data, int index) {
        if (index > data.length) {
            return null;
        }
        Node node = new Node(data[index - 1]);
        // 2 4 5
        // 3 6 7
        // 4 8 9
        // 5 10 11
        // 6 12 13
        // 7
        Node left = createTree(data, 2 * index);
        Node right = createTree(data, 2 * index + 1);
        node.setLeft(left);
        node.setRight(right);
        return node;
    }

    /**
     * 将以root为根节点的二叉树线索化
     */
    public void inThread(Node root) {
        if (root != null) {
            // 线索化左孩子
            inThread(root.getLeft());
            // 左孩子为空
            if (null == root.getLeft()) {
                // 将左孩子设置为线索
                root.setLeftTag(true);
                root.setLeft(pre);
            }
            // 右孩子为空
            if (pre != null && null == pre.getRight()) {
                pre.setRightTag(true);
                pre.setRight(root);
            }
            pre = root;
            // 线索化右孩子
            inThread(root.getRight());
        }
    }

    /**
     * 中序遍历线索二叉树
     */
    public void inThreadList(Node root) {
        if (root != null) {
            // 如果左孩子不是线索
            while (root != null && !root.isLeftTag()) {
                root = root.getLeft();
            }
            do {
                assert root != null;
                System.out.print(root.getData() + ",");
                // 如果右孩子是线索
                if (root.isRightTag()) {
                    root = root.getRight();
                } else {
                    // 有右孩子
                    root = root.getRight();
                    while (root != null && !root.isLeftTag()) {
                        root = root.getLeft();
                    }
                }
            } while (root != null);
        }
    }

    /**
     * 前序遍历递归算法
     */
    public void preList(Node root) {
        if (root != null) {
            System.out.print(root.getData() + ",");
            preList(root.getLeft());
            preList(root.getRight());
        }
    }

    /**
     * 中序遍历
     */
    public void inList(Node root) {
        if (root != null) {
            inList(root.getLeft());
            System.out.print(root.getData() + ",");
            inList(root.getRight());
        }
    }

    @Data
    static class Node {
        int data;
        Node left;
        // 左孩子是否为线索
        boolean leftTag;
        Node right;
        // 右孩子是否为线索
        boolean rightTag;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.leftTag = false;
            this.right = null;
            this.rightTag = false;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node) {
                Node temp = (Node) obj;
                return temp.getData() == this.data;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return super.hashCode() + this.data;
        }
    }
}

package com.java.prepare.until.structure.tree;

import lombok.Data;

import java.util.Scanner;

/**
 * 二叉树
 *
 * @author xuweizhi
 * @since 2020/05/30 16:23
 */
@Data
public class BinaryTree<T> {

    /**
     * 数据结点
     */
    private T data;

    /**
     * 左右子树
     */
    private BinaryTree<T> lChildTree, rChildTree;

    /**
     * 创建一颗二叉树，约定用户按照前序遍历的方式输入数据
     *
     * @param tree 树
     */
    public static <T> BinaryTree<T> createTree(BinaryTree<T> tree, T[] datas) {
        if (TreeUtil.index >= datas.length) {
            return null;
        }
        T data = datas[TreeUtil.index];
        TreeUtil.index++;
        if (data.equals(" ")) {
            return null;
        }
        BinaryTree<T> temp = new BinaryTree<>();
        temp.setData(data);
        if (tree == null) {
            // 创建左子树
            BinaryTree<T> lTree = createTree(temp.getLChildTree(), datas);
            if (lTree != null) {
                temp.setLChildTree(lTree);
            }
            // 创建右子树
            BinaryTree<T> rTree = createTree(temp.getRChildTree(), datas);
            if (rTree != null) {
                temp.setRChildTree(rTree);
            }
        }

        return temp;
    }

    /**
     * 访问二叉树结点的具体操作
     */
    public static <T> void visit(T t, int level) {
        System.out.printf(level + ":" + t + " ");
    }

    public static <T> void reorderTraversal(BinaryTree<T> tree, int level, int mode) {
        if (tree != null) {
            switch (mode) {
                case 1 -> {
                    // 前序
                    visit(tree.getData(), level);
                    reorderTraversal(tree.getLChildTree(), level + 1, mode);
                    reorderTraversal(tree.getRChildTree(), level + 1, mode);
                }
                case 2 -> {
                    // 中序
                    reorderTraversal(tree.getLChildTree(), level + 1, mode);
                    visit(tree.getData(), level);
                    reorderTraversal(tree.getRChildTree(), level + 1, mode);
                }
                case 3 -> {
                    // 后续
                    reorderTraversal(tree.getLChildTree(), level + 1, mode);
                    reorderTraversal(tree.getRChildTree(), level + 1, mode);
                    visit(tree.getData(), level);
                }
            }
        }
    }

    /**
     * ------A--------
     * B----------C---
     * ---D----E-----F
     */
    public static void main(String[] args) {
        int level = 1;
        BinaryTree<String> tree = null;
        //tree = createTree(tree, new String[]{"A", "B", " ", "D", " ", " ", "C", "E", " ", " ", "F"});
        //        A
        //    B      C1
        //  C   D   B1 D1
        //     E F
        tree = createTree(tree, new String[]{"A", "B", "C", " ", " ", "D", "E", " ", " ", "F", " ", " ", "B1", "C1", " ", " ", "D1"});
        reorderTraversal(tree, level, 1);
        System.out.println("");
        reorderTraversal(tree, level, 2);
        System.out.println("");
        reorderTraversal(tree, level, 3);
    }



}

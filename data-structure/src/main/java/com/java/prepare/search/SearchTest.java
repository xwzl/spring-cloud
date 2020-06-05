package com.java.prepare.search;

import org.junit.Test;

/**
 * @author xuweizhi
 * @since 2020/06/04 21:57
 */
public class SearchTest {

    @Test
    public void testFibonacci() {
        int[] a = {0, 1, 16, 24, 35, 47, 59, 62, 73, 88, 99};
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.println(SequentialSearch.fibonacciSearch(a, j, a[i]));
            }
        }

    }

    @Test
    public void testTree() {
        // 主要是表达查询，所以手动构造一棵二叉排序树
        BiTree binaryTree1 = new BiTree();
        binaryTree1.data = 62;

        BiTree binaryTree2 = new BiTree();
        binaryTree1.lChild = binaryTree2;
        binaryTree2.data = 58;

        BiTree binaryTree3 = new BiTree();
        binaryTree2.lChild = binaryTree3;
        binaryTree3.data = 47;

        BiTree binaryTree4 = new BiTree();
        binaryTree3.lChild = binaryTree4;
        binaryTree4.data = 35;

        BiTree binaryTree5 = new BiTree();
        binaryTree4.rChild = binaryTree5;
        binaryTree5.data = 37;

        BiTree binaryTree6 = new BiTree();
        binaryTree3.rChild = binaryTree6;
        binaryTree6.data = 51;

        BiTree binaryTree7 = new BiTree();
        binaryTree1.rChild = binaryTree7;
        binaryTree7.data = 88;

        BiTree binaryTree8 = new BiTree();
        binaryTree7.lChild = binaryTree8;
        binaryTree8.data = 73;

        BiTree binaryTree9 = new BiTree();
        binaryTree7.rChild = binaryTree9;
        binaryTree9.data = 99;

        BiTree binaryTree10 = new BiTree();
        binaryTree9.lChild = binaryTree10;
        binaryTree10.data = 93;

        boolean b = SequentialSearch.insertBinaryTree(binaryTree1, 88);
        System.out.println(b);
    }

    @Test
    public void  testBST( ){
        SequentialSearch.generateBinaryTree();
    }

}

package com.java.prepare.search.tree;

import org.junit.Before;
import org.junit.Test;

public class ThreadTreeTest {
    /**
     * 构建二叉树,添加根节点r
     */
    ThreadedBinaryTree<String> integerThreadedBinaryTree = new ThreadedBinaryTree<>("r");

    /**
     * 构建二叉树
     */
    @Before
    public void buildTree() {
        /*构建二叉树*/
        ThreadedBinaryTree.BinaryTreeNode<String> r = integerThreadedBinaryTree.getRoot();
        //添加r根节点的左子结点a
        ThreadedBinaryTree.BinaryTreeNode<String> a = integerThreadedBinaryTree.addChild(r, "a", true);
        //添加r根节点的右子结点b
        ThreadedBinaryTree.BinaryTreeNode<String> b = integerThreadedBinaryTree.addChild(r, "b", false);
        //添加a节点的左子结点c
        ThreadedBinaryTree.BinaryTreeNode<String> c = integerThreadedBinaryTree.addChild(a, "c", true);
        //添加a节点的右子结点d
        ThreadedBinaryTree.BinaryTreeNode<String> d = integerThreadedBinaryTree.addChild(a, "d", false);
        //添加b节点的左子结点e
        ThreadedBinaryTree.BinaryTreeNode<String> e = integerThreadedBinaryTree.addChild(b, "e", true);
        //添加b节点的右子结点f
        ThreadedBinaryTree.BinaryTreeNode<String> f = integerThreadedBinaryTree.addChild(b, "f", false);
        //添加c节点的左子结点g
        ThreadedBinaryTree.BinaryTreeNode<String> g = integerThreadedBinaryTree.addChild(c, "g", true);
        //添加c节点的右子结点h
        ThreadedBinaryTree.BinaryTreeNode<String> h = integerThreadedBinaryTree.addChild(c, "h", false);
        //添加d节点的左子结点i
        ThreadedBinaryTree.BinaryTreeNode<String> i = integerThreadedBinaryTree.addChild(d, "i", true);
        //添加f节点的左子结点j
        ThreadedBinaryTree.BinaryTreeNode<String> j = integerThreadedBinaryTree.addChild(f, "j", true);
    }


    /**
     * 中序线索二叉树
     */
    @Test
    public void test2() {
        //线索化
        System.out.println(integerThreadedBinaryTree.inThread());
        //线索化之后进行遍历,效率更高
        integerThreadedBinaryTree.inThreadList(integerThreadedBinaryTree.getRoot());
        //g   c   h   a   i   d   r   e   b   j   f
    }
}

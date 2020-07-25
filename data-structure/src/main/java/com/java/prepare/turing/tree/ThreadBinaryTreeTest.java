package com.java.prepare.turing.tree;

import com.java.prepare.turing.tree.ThreadBinaryTree.ThreadNode;
import org.junit.Before;
import org.junit.Test;

/**
 * @author xuweizhi
 * @since 2020/07/25 15:26
 */
public class ThreadBinaryTreeTest {

    ThreadBinaryTree<String> tree = new ThreadBinaryTree<>("r");

    StringBuilder sb = new StringBuilder();

    @Before
    public void init() {
        ThreadNode<String> r = tree.getRoot();
        ThreadNode<String> a = tree.addChild(r, "a", true);
         ThreadNode<String> b = tree.addChild(r, "b", false);
        ThreadNode<String> c = tree.addChild(a, "c", true);
        ThreadNode<String> d = tree.addChild(a, "d", false);
        ThreadNode<String> e = tree.addChild(b, "e", true);
        ThreadNode<String> f = tree.addChild(b, "f", false);
        ThreadNode<String> g = tree.addChild(c, "g", true);
        ThreadNode<String> h = tree.addChild(c, "h", false);
        ThreadNode<String> i = tree.addChild(d, "i", true);
        ThreadNode<String> j = tree.addChild(f, "j", true);
    }

    /**
     * 中序线索二叉树
     */
    @Test
    public void test2() {
        //线索化
        System.out.println(tree.inThread());
        //线索化之后进行遍历,效率更高
        tree.inThreadList((ThreadNode<String>) tree.getRoot());
        //g   c   h   a   i   d   r   e   b   j   f
    }

}

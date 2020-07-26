package com.java.prepare.turing.tree.test;


import com.java.prepare.turing.tree.AbstractBinaryTree.BinaryNode;
import com.java.prepare.turing.tree.LinkedBinaryTree;
import com.java.prepare.turing.tree.LinkedQueue;
import com.java.prepare.turing.tree.Node;
import org.junit.Before;
import org.junit.Test;

/**
 * @author xuweizhi
 * @since 2020/07/25 11:58
 */
public class LinkedBinaryTreeTest {

    LinkedBinaryTree<String> tree = new LinkedBinaryTree<>("r");

    StringBuilder sb = new StringBuilder();

    @Before
    public void init() {
        Node<String> r = tree.getRoot();
        Node<String> a = tree.addChild(r, "a", true);
        Node<String> b = tree.addChild(r, "b", false);
        Node<String> c = tree.addChild(a, "c", true);
        Node<String> d = tree.addChild(a, "d", false);
        Node<String> e = tree.addChild(b, "e", true);
        Node<String> f = tree.addChild(b, "f", false);
        Node<String> g = tree.addChild(c, "g", true);
        Node<String> h = tree.addChild(c, "h", false);
        Node<String> i = tree.addChild(d, "i", true);
        Node<String> j = tree.addChild(f, "j", true);
    }

    /*====================== DFS ======================*/

    /**
     * 深度优先遍历之前序遍历：根左右
     */
    public <E> void preorderTraversal(BinaryNode<E> parent) {
        sb.append(parent.data).append("-->");
        if (parent.left != null) {
            preorderTraversal(parent.left);
        }
        if (parent.right != null) {
            preorderTraversal(parent.right);
        }
    }

    /**
     * 深度优先遍历之中序遍历：根左右
     */
    public <E> void inOrderTraversal(BinaryNode<E> parent) {

        if (parent.left != null) {
            inOrderTraversal(parent.left);
        }
        sb.append(parent.data).append("-->");
        if (parent.right != null) {
            inOrderTraversal(parent.right);
        }
    }

    /**
     * 深度优先遍历之后序遍历：根左右
     */
    public <E> void postOrderTraversal(BinaryNode<E> parent) {
        if (parent.left != null) {
            postOrderTraversal(parent.left);
        }
        if (parent.right != null) {
            postOrderTraversal(parent.right);
        }
        sb.append(parent.data).append("-->");
    }

    /*====================== BFS ======================*/

    /**
     * 广度优先遍历之层序遍历
     */
    public <E> void sequenceTraversal(BinaryNode<E> root) {
        LinkedQueue<BinaryNode<E>> linkedQueue = new LinkedQueue<>();
        linkedQueue.append(root);
        while (!linkedQueue.isEmpty()) {
            BinaryNode<E> first = linkedQueue.getFirst();
            sb.append(first.data).append("-->");
            if (first.left != null) {
                linkedQueue.append(first.left);
            }
            if (first.right != null) {
                linkedQueue.append(first.right);
            }
        }
    }

    /**
     * r–>a–>c–>g–>h–>d–>i–>b–>e–>f–>j
     */
    @Test
    public void preorderTraversalTest() {
        Node<String> root = tree.getRoot();
        tree.checkNullParent(root);
        preorderTraversal((BinaryNode<String>) root);
        String s = sb.toString();
        System.out.println(s.substring(0, s.length() - 3));
    }

    /**
     * g-->c-->h-->a-->i-->d-->r-->e-->b-->j-->f
     */
    @Test
    public void inOrderTraversalTest() {
        Node<String> root = tree.getRoot();
        tree.checkNullParent(root);
        inOrderTraversal((BinaryNode<String>) root);
        String s = sb.toString();
        System.out.println(s.substring(0, s.length() - 3));
    }

    /**
     * g-->h-->c-->i-->d-->a-->e-->j-->f-->b-->r
     */
    @Test
    public void postOrderTraversalTest() {
        Node<String> root = tree.getRoot();
        tree.checkNullParent(root);
        postOrderTraversal((BinaryNode<String>) root);
        String s = sb.toString();
        System.out.println(s.substring(0, s.length() - 3));
    }

    /**
     * r–>a–>b–>c–>d–>e–>f–>g–>h–>i–>j
     */
    @Test
    public void sequenceTraversalTest() {
        Node<String> root = tree.getRoot();
        tree.checkNullParent(root);
        sequenceTraversal((BinaryNode<String>) root);
        String s = sb.toString();
        System.out.println(s.substring(0, s.length() - 3));
    }
}
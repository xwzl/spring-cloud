package com.java.prepare.turing.tree.test;


import com.java.prepare.turing.tree.SortBinaryTree;
import org.junit.Test;

/**
 * @author xuweizhi
 * @since 2020/07/26 12:29
 */
public class SortBinaryTreeTest {

    SortBinaryTree<Integer> tree = new SortBinaryTree<>();

    @Test
    public void addChild() {
        //首先要插入根节点47，然后是第二层的节点16,73，然后是第三层的节点1,24,59,88，然后是第四层的节点20,35,62,77。
        // 每一层内部节点的顺序可以不一致，但是每一层之间的打顺序一定要保持一致，否则虽然中序遍历输出的时候能够正常输出,但是树的结构不能保证。
        Integer[] es = new Integer[]{47, 16, 73, 1, 24, 59, 88, 20, 35, 62, 77};
        for (Integer e : es) {
            tree.insert(e);
        }
        //中序遍历输出
        System.out.println(tree.toInorderTraversalString());

        //查找某个数据是否存在
        System.out.println(tree.contains(1));
        System.out.println(tree.contains(2));
        System.out.println(tree.findMin(tree.getRoot()));
        System.out.println(tree.findMax(tree.getRoot()));
        tree.remove(73);
        System.out.println(tree.toInorderTraversalString());
        tree.remove(25);
        System.out.println(tree.toInorderTraversalString());
    }
}
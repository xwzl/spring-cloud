package com.java.prepare.search.tree;

import org.junit.Test;

/**
 * @author xuweizhi
 * @since 2020/06/05 16:25
 */
public class BinarySearchTreeTest {

    BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();

    @Test
    public void insert() {
        //首先要插入根节点47，然后是第二层的节点16,73，然后是第三层的节点1,24,59,88，然后是第四层的节点20,35,62,77。
        // 每一层内部节点的顺序可以不一致，但是每一层之间的打顺序一定要保持一致，否则虽然中序遍历输出的时候能够正常输出,但是树的结构不能保证。
        Integer[] es = new Integer[]{47, 16, 73, 1, 24, 59, 88, 20, 35, 62, 77};
        binarySearchTree.insert(es);
        //中序遍历输出
        System.out.println(binarySearchTree.toInorderTraversalString());

        //查找某个数据是否存在
        System.out.println(binarySearchTree.contains(1));
        System.out.println(binarySearchTree.contains(2));
        //移除
        binarySearchTree.delete(73);
        //中序遍历输出
        System.out.println(binarySearchTree.toInorderTraversalString());
    }
}

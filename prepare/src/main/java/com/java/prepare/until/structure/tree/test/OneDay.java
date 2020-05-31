package com.java.prepare.until.structure.tree.test;

import com.java.prepare.until.structure.tree.ThreadTree;
import org.junit.Test;

/**
 * @author xuweizhi
 * @since 2020/05/30 22:59
 */
public class OneDay {


    /**
     * 由于它充分利用了空指针域的空间（等于节省了空间），又保证了创建时的一次遍历就可以终生受用前驱后继的信息（这意味着节省了时间），
     * 所以在实际问题中，如果所使用的二叉树需要经常遍历或查找结点时需要某种遍历序列中的前驱和后继，那么采用线索二叉链表的存储结构就是
     * 不错的选择
     */
    @Test
    public void testThreadTree() {
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        // 创建普通二叉树
        ThreadTree threadTree = new ThreadTree(data);
        // 中序递归遍历二叉树
        threadTree.inList(threadTree.getRoot());
        System.out.println();
        // 采用中序遍历将二叉树线索化
        threadTree.inThread(threadTree.getRoot());
        // 中序遍历线索化二叉树
        threadTree.inThreadList(threadTree.getRoot());
    }

}

package com.java.prepare.search.tree;

import com.java.prepare.search.tree.HuffmanTree.BinaryTreeNode;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class HuffmanTreeTest {

    /*采用用普通集合和最小堆都行,最大的区别是它们的采用不同的排序算法,效率是不一致的*/

    /**
     * 采用普通list作为临时存储节点数据的集合,因此我们需要手动排序
     */
    @Test
    public void test1() {
        //采用普通list作为临时存储节点数据的集合,因此我们需要手动排序
        List<BinaryTreeNode<String>> binaryTreeNodes = new ArrayList<>();
        // A5,B15,C40,D30,E10
        binaryTreeNodes.add(new BinaryTreeNode<>("A", "5"));
        binaryTreeNodes.add(new BinaryTreeNode<>("B", "15"));
        binaryTreeNodes.add(new BinaryTreeNode<>("C", "40"));
        binaryTreeNodes.add(new BinaryTreeNode<>("D", "30"));
        binaryTreeNodes.add(new BinaryTreeNode<>("E", "10"));
        HuffmanTree<String> huffmanTree = HuffmanTree.build(binaryTreeNodes);
        BinaryTreeNode<String> root = huffmanTree.getRoot();
        System.out.println(root);
    }

    /**
     * 采用 最小堆--priorityQueue 作为临时存储节点数据的集合,priorityQueue的性质就是对集合的元素进行自动排序,因此我们不必手动排序
     */
    @Test
    public void test2() {
        //采用最小堆--priorityQueue.作为临时存储节点数据的集合,priorityQueue的性质就是对集合的元素进行自动排序,我们只需要指定排序规则
        PriorityQueue<BinaryTreeNode<String>> priorityQueueBinaryTreeNodes = new PriorityQueue<>((o1, o2) -> new BigDecimal(o1.weight).subtract(new BigDecimal(o2.weight)).intValue());
        priorityQueueBinaryTreeNodes.add(new BinaryTreeNode<>("A", "5"));
        priorityQueueBinaryTreeNodes.add(new BinaryTreeNode<>("B", "15"));
        priorityQueueBinaryTreeNodes.add(new BinaryTreeNode<>("C", "40"));
        priorityQueueBinaryTreeNodes.add(new BinaryTreeNode<>("D", "30"));
        priorityQueueBinaryTreeNodes.add(new BinaryTreeNode<>("E", "10"));
        HuffmanTree<String> huffmanTree = HuffmanTree.build(priorityQueueBinaryTreeNodes);
        BinaryTreeNode<String> root = huffmanTree.getRoot();
        System.out.println(root);
    }
}

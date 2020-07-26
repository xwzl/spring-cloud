package com.java.prepare.turing.tree.test;


import com.java.prepare.turing.tree.HuffmanTree;
import com.java.prepare.turing.tree.HuffmanTree.HuffmanNode;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 测试
 *
 * @author xuweizhi
 * @since 2020/07/25 18:23
 */
public class HuffmanTreeTest {

    /*采用用普通集合和最小堆都行,最大的区别是它们的采用不同的排序算法,效率是不一致的*/

    /**
     * 采用普通list作为临时存储节点数据的集合,因此我们需要手动排序
     */
    @Test
    public void test1() throws IOException {
        //采用普通list作为临时存储节点数据的集合,因此我们需要手动排序

        StringBuilder source = new StringBuilder();
        //for (int i = 0; i < 20000; i++) {
        //    source.append(UUID.randomUUID().toString());
        //}

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\62424" +
                "\\Desktop\\1.txt"), "GBK"));

        String str = null;
        while ((str = reader.readLine()) != null) {
            source.append(str);
        }
        HuffmanTree<String> huffmanTree = new HuffmanTree<>(source.toString());
        huffmanTree.build(huffmanTree.initNodes());
        huffmanTree.buildHuffmanCode();
        String encode = huffmanTree.encodeCache();
        long start = System.currentTimeMillis();
        //System.out.println(encode);
        String x = huffmanTree.toStringCode(encode);
        //System.out.println(x);
        int i = source.toString().length() * 3 * 8;
        int length = encode.length();
        System.out.println("编码前：" + i);
        System.out.println("编码后：" + length);
        System.out.println("编码效率：" + i / length);
        System.out.println("时间" +(System.currentTimeMillis() - start));

    }

    /**
     * 采用 最小堆--priorityQueue 作为临时存储节点数据的集合,priorityQueue的性质就是对集合的元素进行自动排序,因此我们不必手动排序
     */
    @Test
    public void test2() {
        //采用最小堆--priorityQueue.作为临时存储节点数据的集合,priorityQueue的性质就是对集合的元素进行自动排序,我们只需要指定排序规则
        PriorityQueue<HuffmanNode<String>> nodes = new PriorityQueue<>();
        // A5,B15,C40,D30,E10
        nodes.add(new HuffmanNode<>("A", 5));
        nodes.add(new HuffmanNode<>("E", 10));
        nodes.add(new HuffmanNode<>("B", 15));
        nodes.add(new HuffmanNode<>("C", 40));
        nodes.add(new HuffmanNode<>("D", 30));
        HuffmanTree<String> huffmanTree = new HuffmanTree<>("");
        huffmanTree.build(nodes);
        HuffmanNode<String> root = huffmanTree.getRoot();
        System.out.println(root.toString());
    }

}
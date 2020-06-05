package com.java.prepare.tree.huff;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

/**
 * 哈夫曼编码：http://note.youdao.com/noteshare?id=8fcafb3ef9d785cb7a5397081baf31ff&sub=3475491DEADA4BD0ADB1C3313F72691B
 * <p>
 * 通过重新定义字符编码出现的次数（权值进行编码）
 *
 * @author xuweizhi
 * @since 2020/05/31 21:11
 */
public class HuffmanTree {

    /**
     * 用来存储不同字符对应的权重（出现次数）
     */
    private static final HashMap<Character, Integer> mapTimes = new HashMap<>();
    /**
     * 用来存储不同字符对应的编码
     */
    private static final HashMap<Character, String> mapCode = new HashMap<>();
    /**
     * 定义一个森林，用来存储二叉树
     */
    private static final ArrayList<Node> list = new ArrayList<>();
    /**
     * 为第二个例子服务，计算去掉空格后有多少个字母
     */
    private static double num = 0;

    /**
     * 统计输入字符串里每一个字符出现的次数，并存入map集合中
     *
     * @param str      字符串
     * @param mapTimes 统计次数
     */
    public static void statisticsTimes(String str, HashMap<Character, Integer> mapTimes) {
        char[] ch = str.toCharArray();
        for (char c : ch) {   //遍历ch数组
            for (int i = 'A'; i <= 'Z'; i++) {   //遍历大写字母
                if (c == (char) i) {
                    if (mapTimes.containsKey(c)) {   //map集合中已经存在该字母
                        num++;
                        Integer value = mapTimes.get(c);
                        mapTimes.put(c, ++value);
                    } else {                         //map集合中还没有该字母
                        num++;
                        mapTimes.put(c, 1);
                    }
                }
            }
            for (int i = 'a'; i <= 'z'; i++) {  //遍历小写字母
                if (c == (char) i) {
                    if (mapTimes.containsKey(c)) {   //map集合中已经存在该字母
                        num++;
                        Integer value = mapTimes.get(c);
                        mapTimes.put(c, ++value);
                    } else {                         //map集合中还没有该字母
                        num++;
                        mapTimes.put(c, 1);
                    }
                }
            }
        }
    }

    /**
     * 利用map集合构建哈夫曼树
     *
     * @param mapTimes 次数
     * @param list     集合
     */
    public static void buildHuffmanTree(HashMap<Character, Integer> mapTimes, ArrayList<Node> list) {
        Node leftChild;
        Node rightChild;

        Set<Character> set = mapTimes.keySet();    //遍历map集合
        for (Character key : set) {
            Integer value = mapTimes.get(key);
            list.add(new Node(key, value));         //以权值作为根节点构建n棵二叉树，组成森林
        }

        while (list.size() != 1) {
            Collections.sort(list);                 //让节点的权值从小到大排序
            leftChild = list.remove(0);
            leftChild.setCode("0");
            rightChild = list.remove(0);
            rightChild.setCode("1");
            list.add(new Node(leftChild.getValue() + rightChild.getValue(), leftChild, rightChild));
        }
    }

    /**
     * 遍历哈夫曼树，并且进行编码
     *
     * @param node 结点
     */
    public static void buildHuffmanCode(Node node) {
        if (node.getLeftChild() != null) {
            node.getLeftChild().setCode(node.getCode() + node.getLeftChild().getCode());
            buildHuffmanCode(node.getLeftChild());
        }
        if (node.getRightChild() != null) {
            node.getRightChild().setCode(node.getCode() + node.getRightChild().getCode());
            buildHuffmanCode(node.getRightChild());
        }
    }

    /**
     * 打印哈夫曼编码
     *
     * @param node 结点
     */
    public static void printHuffmanCode(Node node) {
        if (node != null) {
            if (node.getLeftChild() == null && node.getRightChild() == null) {

                if (node.getValue() >= 0 && node.getValue() <= 9) {
                    System.out.print(node.getCh() + "的出现次数是: " + node.getValue());
                } else {
                    System.out.print(node.getCh() + "的出现次数是:" + node.getValue());
                }
                System.out.println("        对应的编码是:" + node.getCode());
                mapCode.put(node.getCh(), node.getCode());
            } else {
                printHuffmanCode(node.getLeftChild());
                printHuffmanCode(node.getRightChild());
            }
        }
    }

    /**
     * 打印编码压缩效率
     *
     * @param mapTimes
     * @param str
     */
    public static void printCompressionRate(HashMap<Character, Integer> mapTimes, HashMap<Character, String> mapCode, String str) {
        double numBefore = 0;
        double numAfter = 0;
        double times;
        String code = null;
        for (Character key : mapTimes.keySet()) {
            times = mapTimes.get(key);
            code = mapCode.get(key);
            numAfter += times * (code.length());
        }
        numBefore = 8 * num;
        System.out.println("==================================================================");
        System.out.println("编码压缩效率:" + numBefore / numAfter);
    }

    /**
     * 测试方法
     *
     * @param args
     */
    public static void main(String[] args) {
        /*需要编码的字符串*/
        //private static String str = "dcbaddcdddcddcab";
        //下面这个例子待定，因为不确定是否需要把空格也算上
        String str = "ABBCCCDDDDEEEEE";
        statisticsTimes(str, mapTimes);    //统计每个字符出现的次数
        buildHuffmanTree(mapTimes, list);  //构建哈夫曼树
        buildHuffmanCode(list.get(0));      //遍历哈夫曼树并且进行编码
        printHuffmanCode(list.get(0));      //打印哈夫曼编码
        printCompressionRate(mapTimes, mapCode, str);    //打印编码压缩效率
    }


    /**
     * 节点类
     */
    @Data
    static class Node implements Comparable {
        private Character ch;//存储的字母
        private Integer value;//对应的权值
        private Node leftChild;//左子节点
        private Node rightChild;//右子节点
        private String code = "";//对应的哈夫曼编码

        public Node(Character ch, Integer value) {
            this.ch = ch;
            this.value = value;
        }

        public Node(Integer value, Node leftChild, Node rightChild) {
            this.value = value;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "ch=" + ch +
                    ", value=" + value +
                    ", leftChild=" + leftChild +
                    ", rightChild=" + rightChild +
                    '}';
        }

        @Override
        public int compareTo(Object o) {
            Node that = (Node) o;
            return this.value - that.value;
        }
    }

}

package com.java.prepare.tree.huff;

import com.java.prepare.Huffman;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <h1>哈夫曼树</h1>
 * 思路：
 * <ul>
 *     <li>1、构建 huffman 结点和权重数据结构存储</li>
 *     <li>2、初始化权重队列</li>
 *     <li>3、构建huffman 树，初始化编码</li>
 *     <li>4、编码</li>
 *     <li>5、解码</li>
 *     <li>6、打印压缩效率</li>
 * </ul>
 *
 * @author xuweizhi
 * @since 2020/06/01 9:55
 */
@Data
@Slf4j
public class HuffmanImpl implements Huffman {

    /**
     * 存放字符出现的次数
     */
    private Map<String, Integer> charList = new HashMap<>();

    private LinkedList<HNode> nodeList;

    private HNode root;

    private int level = 0;

    @Override
    public void createHuffmanTree(String str) {
        // 统计字符出现的次数
        getCharNum(str);

        // 生成结点
        createNodes();

        // 创建 huffman 树
        buildHuffmanTree();

        root = nodeList.get(0);
    }

    /**
     * 由于对 nodeList 排序，因此 0 和 1 个元素肯定是权值最低的
     * <p>
     * 左右的值比右边的少
     */
    private void buildHuffmanTree() {
        while (nodeList.size() > 1) {
            Collections.sort(nodeList);
            //sort(nodeList);
            // 左边的节点
            HNode left = nodeList.poll();
            // 右边的结点
            HNode right = nodeList.poll();
            assert right != null;
            assert left != null;

            left.code = "0";
            right.code = "1";

            // 可以单独构建编码
            //setCode(left);
            //setCode(right);

            HNode e = new HNode(left, right, left.num + right.num);
            // 字符打印顺序
            nodeList.addFirst(e);
        }
        if (nodeList.size() == 1) {
            setCode(nodeList.get(0));
        }
    }

    private void setCode(HNode root) {
        // 填充左孩子路径
        if (root.getLeftNode() != null) {
            root.leftNode.code = root.code + "0";
            setCode(root.leftNode);
        }
        if (root.rightNode != null) {
            // 实现路径的拼接
            root.rightNode.code = root.code + "1";
            setCode(root.rightNode);
        }
    }

    /**
     * 获取打印次数
     *
     * @param str 源码
     */
    @Override
    public void getCharNum(String str) {
        char[] chars = str.toCharArray();
        for (char c : chars) {
            // 感觉和牛逼的样子
            charList.merge(c + "", 1, Integer::sum);
        }
    }

    /**
     * 生成 node 节点
     */
    private void createNodes() {
        nodeList = charList.entrySet().stream().map(entry -> new HNode(entry.getKey(), entry.getValue()))
                .collect(Collectors.toCollection(LinkedList::new));

    }

    /**
     * 排序
     *
     * @param nodeList 结点
     */
    private void sort(LinkedList<HNode> nodeList) {
        for (int i = 0; i < nodeList.size() - 1; i++) {
            for (int j = i + 1; j < nodeList.size(); j++) {
                HNode temp;
                if (nodeList.get(i).num > nodeList.get(j).num) {
                    temp = nodeList.get(i);
                    nodeList.set(i, nodeList.get(j));
                    nodeList.set(j, temp);
                }
            }
        }

    }

    /**
     * 打印编码集
     */
    private void printCharNum() {
        for (Map.Entry<String, Integer> entry : charList.entrySet()) {
            log.info("{}:{}", entry.getKey(), entry.getValue());
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        HuffmanImpl huffman = new HuffmanImpl();
        String data = "17:55:42.802 [main] INFO com.java.prepare.until.structure.tree.huff.HuffmanImpl - data:E,code:11,level:4,num:5";
        huffman.createHuffmanTree(data);
        huffman.printHufCode();
        String hufCode = huffman.stringToHuf(data);
        log.info("编码：" + hufCode);
        log.info("解码：" + huffman.hufToString(hufCode));
    }

    private boolean target = false;

    /**
     * 哈夫曼编码转换为字符串
     *
     * @param hufCode 哈夫曼编码
     */
    private String hufToString(String hufCode) {
        int start = 0;
        int end = 1;
        cleanBuilder();
        for (int i = 0; i < hufCode.length(); i++) {
            String s = hufCode.substring(start, end);
            target = false;
            match(root, s);
            if (target) {
                start = end;
            }
            // 每次 end 自增
            end++;
        }
        return sb.toString();
    }

    private void match(HNode node, String s) {
        if (node.leftNode == null && node.rightNode == null) {
            if (s.equals(node.code)) {
                sb.append(node.getData());
                target = true;
            }
        }
        if (node.leftNode != null) {
            match(node.leftNode, s);
        }
        if (node.rightNode != null) {
            match(node.rightNode, s);
        }
    }

    private void cleanBuilder() {
        sb.delete(0, sb.length());
    }

    /**
     * 字符串转huffCode
     */
    private StringBuilder sb = new StringBuilder();

    /**
     * 字符串转哈夫编码
     *
     * @param data 数据
     * @return 返回值
     */
    private String stringToHuf(String data) {
        for (int i = 0; i < data.length(); i++) {
            search(data.charAt(i) + "", root);
        }
        return sb.toString();
    }

    /**
     * 编码
     *
     * @param data 数据
     * @param root 头结点
     */
    private void search(String data, HNode root) {
        if (root.rightNode == null && root.leftNode == null) {
            if (data.equals(root.data)) {
                sb.append(root.code);
            }
        }
        if (root.leftNode != null) {
            search(data, root.leftNode);
        }
        if (root.rightNode != null) {
            search(data, root.rightNode);
        }
    }

    /**
     * 打印 hufCode
     */
    private void printHufCode() {
        printHufCode(root);
    }

    /**
     * 打印哈夫曼编码
     *
     * @param node 当前结点
     */
    private void printHufCode(HNode node) {
        if (node.leftNode == null && node.rightNode == null) {
            //System.out.println(node.data + ":" + node.code);
            log.info("data:{},code:{},level:{},num:{}", node.data, node.code, level, node.num);
            level--;
        }
        if (node.leftNode != null) {
            level++;
            printHufCode(node.leftNode);
        }
        if (node.rightNode != null) {
            level++;
            printHufCode(node.rightNode);
        }
    }

    @Data
    public static class HNode implements Comparable<HNode> {
        // str 出现次数
        private int num;
        // str 字符
        private String data;
        // 编码
        private String code = "";
        // 左节点
        private HNode leftNode;
        // 右节点
        private HNode rightNode;

        public HNode(String data, int num) {
            this.num = num;
            this.data = data;
        }

        public HNode(HNode leftNode, HNode rightNode, int num) {
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.num = num;
        }

        @Override
        public int compareTo(@NotNull HuffmanImpl.HNode that) {
            return this.num - that.num;
        }
    }


}

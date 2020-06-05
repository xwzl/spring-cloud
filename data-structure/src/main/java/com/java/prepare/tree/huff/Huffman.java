package com.java.prepare.tree.huff;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 哈夫曼树
 *
 * @author xuweizhi
 * @since 2020/05/31 21:51
 */
public class Huffman {

    // 哈夫曼二叉树的根节点
    private HNode root;

    // 存储不同字符的队列 相同字符存在同一位置
    private LinkedList<CharData> charList;

    // 存储节点的队列
    private LinkedList<HNode> nodeList;

    private Map<String, String> cache;

    static class CharData {
        int num = 1; // 字符个数
        char c; // 字符

        public CharData(char ch) {
            c = ch;
        }
    }

    /**
     * 构建哈夫曼树
     *
     * @param str 字符串
     */
    public void creatHfmTree(String str) {
        // 最初用于压缩的字符串
        nodeList = new LinkedList<>();
        charList = new LinkedList<>();

        // 1.统计字符串中字符以及字符的出现次数
        // 以CharData类来统计出现的字符和个数
        getCharNum(str);

        // 2.根据第一步的结构，创建节点
        creatNodes();

        // 3.对节点权值升序排序
        sort(nodeList);

        // xwz
        cache = new HashMap<>(nodeList.size());

        // 4.取出权值最小的两个节点，生成一个新的父节点
        // 5.删除权值最小的两个节点，将父节点存放到列表中
        creatTree();

        // 6.重复第四五步，就是那个while循环
        // 7.将最后的一个节点赋给根节点
        root = nodeList.get(0);
    }

    /**
     * 统计出现的字符及其频率
     *
     * @param str
     */
    private void getCharNum(String str) {

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i); // 从给定的字符串中取出字符
            // 最新的字符是否已经存在的标签
            boolean flag = true;

            for (int j = 0; j < charList.size(); j++) {
                CharData data = charList.get(j);
                if (ch == data.c) {
                    // 字符对象链表中有相同字符则将个数加1
                    data.num++;
                    flag = false;
                    break;
                }
            }

            if (flag) {
                // 字符对象链表中没有相同字符则创建新对象加如链表
                charList.add(new CharData(ch));
            }

        }

    }

    /**
     * 将出现的字符创建成单个的结点对象
     */
    private void creatNodes() {

        for (int i = 0; i < charList.size(); i++) {
            String data = charList.get(i).c + "";
            int count = charList.get(i).num;

            HNode node = new HNode(data, count); // 创建节点对象
            nodeList.add(node); // 加入到节点链表
        }

    }

    /**
     * 构建哈夫曼树
     */
    private void creatTree() {

        while (nodeList.size() > 1) {// 当节点数目大于一时
            // 4.取出权值最小的两个节点，生成一个新的父节点
            // 5.删除权值最小的两个节点，将父节点存放到列表中
            HNode left = nodeList.poll();
            HNode right = nodeList.poll();

            // 在构建哈夫曼树时设置各个结点的哈夫曼编码
            left.code = "0";
            right.code = "1";
            setCode(left);
            setCode(right);

            int parentWeight = left.count + right.count;// 父节点权值等于子节点权值之和
            HNode parent = new HNode(parentWeight, left, right);

            nodeList.addFirst(parent); // 将父节点置于首位
            sort(nodeList); // 重新排序，避免新节点权值大于链表首个结点的权值
        }
    }

    /**
     * 升序排序
     *
     * @param nodeList 带有权值的队列或者集合
     */
    private void sort(LinkedList<HNode> nodeList) {
        for (int i = 0; i < nodeList.size() - 1; i++) {
            for (int j = i + 1; j < nodeList.size(); j++) {
                HNode temp;
                if (nodeList.get(i).count > nodeList.get(j).count) {
                    temp = nodeList.get(i);
                    nodeList.set(i, nodeList.get(j));
                    nodeList.set(j, temp);
                }
            }
        }

    }

    /**
     * 设置结点的哈夫曼编码
     *
     * @param root
     */
    private void setCode(HNode root) {

        if (root.lChild != null) {
            root.lChild.code = root.code + "0";
            setCode(root.lChild);
        }

        if (root.rChild != null) {
            root.rChild.code = root.code + "1";
            setCode(root.rChild);
        }
    }

    /**
     * 遍历
     *
     * @param node 节点
     */
    private void output(HNode node) {

        if (node.lChild == null && node.rChild == null) {
            System.out.println(node.data + ": " + node.code);
            // 添加缓存
            cache.put(node.data, node.code);
        }
        if (node.lChild != null) {
            output(node.lChild);
        }
        if (node.rChild != null) {
            output(node.rChild);
        }
    }

    /**
     * 输出结果字符的哈夫曼编码
     */
    public void output() {
        output(root);
    }


    /***********************以下是编解码的实现*************************/

    private String hfmCodeStr = "";// 哈夫曼编码连接成的字符串,StringBuilder 做

    /**
     * 编码
     *
     * @param str
     * @return
     */
    public String toHufCode(String str) {

        for (int i = 0; i < str.length(); i++) {
            String c = str.charAt(i) + "";
            // 貌似没啥意义，最快遍历，貌似 map 没啥意义
            search(root, c);
            // map 实现遍历的过程
            //toHufCodeMapCache(c);
        }

        return hfmCodeStr;
    }

    // 貌似没啥意义，可能更慢
    private void toHufCodeMapCache(String c) {
        String code = cache.get(c);
        hfmCodeStr = hfmCodeStr + code;
    }

    /**
     * @param root 哈夫曼树根节点
     * @param c    需要生成编码的字符
     */
    private void search(HNode root, String c) {
        if (root.lChild == null && root.rChild == null) {
            if (c.equals(root.data)) {
                hfmCodeStr += root.code; // 找到字符，将其哈夫曼编码拼接到最终返回二进制字符串的后面
            }
        }
        if (root.lChild != null) {
            search(root.lChild, c);
        }
        if (root.rChild != null) {
            search(root.rChild, c);
        }
    }


    // 保存解码的字符串
    String result = "";
    boolean target = false; // 解码标记

    /**
     * 解码
     *
     * @param codeStr
     * @return
     */
    public String codeToString(String codeStr) {

        int start = 0;
        int end = 1;

        while (end <= codeStr.length()) {
            target = false;
            String s = codeStr.substring(start, end);
            matchCode(root, s); // 解码
            // 每解码一个字符，start向后移
            if (target) {
                start = end;
            }
            end++;
        }

        return result;
    }

    /**
     * 匹配字符哈夫曼编码，找到对应的字符
     *
     * @param root 哈夫曼树根节点
     * @param code 需要解码的二进制字符串
     */
    private void matchCode(HNode root, String code) {
        if (root.lChild == null && root.rChild == null) {
            if (code.equals(root.code)) {
                result += root.data; // 找到对应的字符，拼接到解码字符穿后
                target = true; // 标志置为true
            }
        }
        if (root.lChild != null) {
            matchCode(root.lChild, code);
        }
        if (root.rChild != null) {
            matchCode(root.rChild, code);
        }

    }

    public static void main(String[] args) {
        Huffman huff = new Huffman();// 创建哈弗曼对象
        // 读取本地文件（自己写的方法，不想写可以自己随便定一个字符串）
        //String data = readFile();
        String data = "ABBCCCDDDDEEEEE";
        huff.creatHfmTree(data);// 构造树
        huff.output(); // 显示字符的哈夫曼编码
        // 将目标字符串利用生成好的哈夫曼编码生成对应的二进制编码
        String hufCode = huff.toHufCode(data);
        System.out.println("编码:" + hufCode);
        // 将上述二进制编码再翻译成字符串
        System.out.println("解码：" + huff.codeToString(hufCode));

    }


    /**
     * 节点类
     *
     * @author LiRui
     */
    static class HNode {

        public String code = "";// 节点的哈夫曼编码
        public String data = "";// 节点的数据
        public int count;// 节点的权值
        public HNode lChild;
        public HNode rChild;

        public HNode() {
        }

        public HNode(String data, int count) {
            this.data = data;
            this.count = count;
        }

        public HNode(int count, HNode lChild, HNode rChild) {
            this.count = count;
            this.lChild = lChild;
            this.rChild = rChild;
        }

        public HNode(String data, int count, HNode lChild, HNode rChild) {
            this.data = data;
            this.count = count;
            this.lChild = lChild;
            this.rChild = rChild;
        }

    }

}

package com.java.prepare.tree.huff;

import java.util.*;

public class HuffmanTreeCode {

    /**
     * 获取 huffman 编码
     *
     * @param bytes 数组
     * @return 编码
     */
    public static List<HCNode> getNodes(byte[] bytes) {
        // 创建
        ArrayList<HCNode> nodes = new ArrayList<HCNode>();
        // 遍历bytes统计每个字母出现的次数 map[k->v]
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            //Integer count = counts.get(b);
            //if (count == null) { // 还不存在这个key
            //    counts.put(b, 1);
            //} else {
            //    counts.put(b, count + 1);
            //}
            // 还不存在这个key
            counts.merge(b, 1, Integer::sum);
        }
        // 把键值对转成HCNode对象
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new HCNode(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    public static HCNode create(List<HCNode> nodes) {

        while (nodes.size() > 1) {
            // 先进行排序
            Collections.sort(nodes);

            // 取出最小的俩个数据
            HCNode leftnode = nodes.get(0);
            HCNode rightnode = nodes.get(1);

            // 构建新的二叉树
            HCNode parent = new HCNode(null, leftnode.weight + rightnode.weight);
            parent.left = leftnode;
            parent.right = rightnode;

            // 从当中删除使用过的子节点
            nodes.remove(leftnode);
            nodes.remove(rightnode);

            // 把父节点加入进去
            nodes.add(parent);
        }
        // System.out.println("第一次处理后：" +nodes);
        return nodes.get(0);
    }

    // 通过赫夫曼树生成0和1的编码
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmancode) {
        // 赫夫曼编码表转成编码后的编码
        StringBuilder StringBuilder = new StringBuilder();
        // 遍历bytes
        for (byte b : bytes) {
            StringBuilder.append(huffmancode.get(b));
        }
        System.out.println("StringBuilder =" + StringBuilder);
        // 把 01100110101101110010110111110000 转成byte[]
        int len;
        if (StringBuilder.length() % 8 == 0) {
            len = StringBuilder.length() / 8;
        } else {
            len = StringBuilder.length() / 8 + 1;
        }
        // 创建存储压缩后的huffmanCodeByte
        byte[] huffmanCodeByte = new byte[len];
        int index = 0;// 记录下来是第几个byte
        for (int i = 0; i < StringBuilder.length(); i += 8) { // 每8位对应一个byte，步长为8
            String strByte;
            if (i + 8 > StringBuilder.length()) { // 最后一个不够8位
                strByte = StringBuilder.substring(i);
            } else {
                strByte = StringBuilder.substring(i, i += 8);
            }
            // strByte转换成byte
            huffmanCodeByte[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeByte;
    }

    // 赫夫曼树对应的编码表,放在map当中。
    // 拼接路径,定义一个StringBuilder存储叶子节点的路径
    static Map<Byte, String> huffmanCode = new HashMap<Byte, String>();
    static StringBuilder StringBuilder = new StringBuilder();

    // node 当前节点 code 路径 左0右1 StringBuilder用于路径拼接
    public static void getCode(HCNode node, String code, StringBuilder StringBuilder) {
        StringBuilder StringBuilder2 = new StringBuilder(StringBuilder);
        // 把code加入到StringBuilder2
        StringBuilder2.append(code);
        if (node != null) {
            // 判断节点是不是叶子节点
            if (node.data == null) {// 非叶子节点
                // 向左递归处理
                getCode(node.left, "0", StringBuilder2);
                // 向右递归处理
                getCode(node.right, "1", StringBuilder2);
            } else {
                huffmanCode.put(node.data, StringBuilder2.toString());
            }
        }
    }

    public static Map<Byte, String> getCode(HCNode root) {
        if (root == null) {
            return null;
        }
        getCode(root.left, "0", StringBuilder);
        getCode(root.right, "1", StringBuilder);
        return huffmanCode;
    }

    // 前序遍历
    public static void DLR(HCNode root) {
        if (root != null) {
            root.DLR();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }


    public static void main(String[] args) {
        String str = "hello world";
        // String str="i like like like java do you like a java";
        byte[] strBytes = str.getBytes();
        System.out.println("StrBytes = " + Arrays.toString(strBytes));
        byte[] hCode = huffmanZip(strBytes);
        System.out.println("hCode =" + Arrays.toString(hCode) + " , 长度" + hCode.length);
    }

    static class HCNode implements Comparable<HCNode> {
        Byte data;
        // 节点的值、权值
        int weight;
        // 左右子节点的指针
        HCNode left;
        HCNode right;

        public HCNode(Byte data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        // 前序遍历
        public void DLR() {
            System.out.println(this);// 先输出根节点
            // 左子树递归
            if (this.left != null) {
                this.left.DLR();
            }
            // 右子树递归
            if (this.right != null) {
                this.right.DLR();
            }
        }

        @Override
        public String toString() {
            return "HCNode[ data =" + data + ", weight = " + weight + "]";
        }

        @Override
        public int compareTo(HCNode o) {
            return this.weight - o.weight; // 从小到大排序
        }
    }

    /**
     * 解码
     *
     * @param flag 标志
     * @param b    编码
     * @return 解码
     */
    public static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        if (flag) { // 用于判断是否需要补位
            // 如果是正数还需要进行补位
            temp |= 256; // 按位与 256 = 1 0000 0000 | 0000 0001 = 1 0000 0001
        }
        String str = Integer.toBinaryString(temp); // 返回的是temp补码
        // System.out.println("str = " + str);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    private static byte[] huffmanZip(byte[] bytes) {
        List<HCNode> nodes = getNodes(bytes);
        // 创建赫夫曼树
        HCNode root = create(nodes);
        // root.DLR();
        // 生成赫夫曼编码
        Map<Byte, String> huffmanCode = getCode(root);
        return zip(bytes, huffmanCode);
    }

}

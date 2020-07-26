package com.java.prepare.turing.tree;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * <h1>Node</h1>
 *
 * <ul>
 *     <li>权：在数据结构中，实体有节点（元素）和边（关系）两大类，所以对应有节点权和边权</li>
 *     <li>路径：在一棵树中，一个结点到另一个结点之间的通路，称为路径。上图，从根结点到结点 h之间的通路就是一条路径。</li>
 *     <li>节点路径长度：在一棵树中，从一个节点到另一个节点所经过的“边”的数量，被我们称为两个结点之间的
 *     路径长度；或者说路径上的分支数目称作路径长度</li>
 *     <li>树的路径长度：树的路径长度就是从树根到每一结点的路径长度之和</li>
 *     <li>节点的带权路径长度：树的每一个结点，都可以拥有自己的“权重”（Weight），权重在不同的算法当中可以起到不同的
 *     作用。结点的带权路径长度，是指树的根结点到该结点的路径长度，和该结点权重的乘积。</li>
 *     <li>树的带权路径长度:树中所有叶子结点的带权路径长度之和。也被简称为WPL</li>
 * </ul>
 * <p>
 * 哈夫曼树的构建方法被称为哈夫曼算法，其构建步骤为:
 * <ul>
 *      <li>根据给定的n个权值{w1,w2,…,wn}构成n棵二叉树的集合F={T1,T2,…,Tn}，其中每棵二叉树Ti中只有
 *      一个带权为wi根结点，其左右子树均为空</li>
 *      <li>在F中选取两棵根结点的权值最小的树作为左右子树构造一棵新的二叉树，且置新的二叉树的根结点的权
 *      值为其左右子树上根结点的权值之和,</li>
 *      <li>在F中删除这两棵树，同时将新得到的二叉树加入F中</li>
 *      <li>重复2和3步骤，直到F只含一棵树为止。这棵树便是哈夫曼树</li>
 * </ul>
 * <p>
 * 注意
 * <ul>
 *     <li>叶子上的权值均相同时，完全二叉树一定是最优二叉树，否则完全二叉树不一定是最优二叉树。</li>
 *     <li>最优二叉树中，权越大的叶子离根越近</li>
 *     <li>最优二叉树的WPL最小，但是形态不唯一</li>
 * </ul>
 *
 * <h2>案例</h2>
 * <p>
 * 有五个带权节点{ A5,B15,C40,D30,E10}。要求构建哈夫曼树。
 *  <ul>
 *      <li>1. 先把有权值的叶子结点按照从小到大的顺序排列成一个有序序列，即：A5，E10，B15，D30，C40。</li>
 *      <li>2. 取头两个最小权值的结点作为一个新节点N1的两个子结点，取相对较小的是左孩子（实际上也可以不遵守，
 *      因为哈夫曼树是有多种形状的，但是WPL都是最小的），这里就是A为N1的左孩子，E为N1的右孩子</li>
 *      <li>3. 新结点的权值为两个叶子权值的和5+10=15</li>
 *      <li>4. 将N1替换A与E，插入有序序列中，保持从小到大排列。即：N115，B15，D30，C40</li>
 *      <li>5. 重复步骤2和3。将N1与B作为一个新节点N2的两个子结点。N2的权值=15+15=30</li>
 *      <li>6. 将N2替换N1与B，插入有序序列中，保持从小到大排列。即：N230，D30，C40</li>
 *      <li>7. 重复步骤2和3。将N2与D作为一个新节点N3的两个子结点。N3的权值=30+30=60</li>
 *      <li>8. 将N3替换N2与D，插入有序序列中，保持从小到大排列。即：C40，N360</li>
 *      <li>9. 重复步骤2和3，将C与N3作为一个新节点T的两个子结点，如下图，由于T即是根结点，完成哈夫曼树的构造。</li>
 *  </ul>
 * <p>
 *  此时的上图二叉树的带权路径长度WPL=40×1+30×2+15×3+10×4+5×4=205。这就是最短带权路径长度。
 *
 * @author xuweizhi
 * @since 2020/07/25 17:24
 */
public class HuffmanTree<E> extends AbstractBinaryTree<E> implements BinaryTree<E> {

    private final String source;

    private StringBuilder sb;

    private int length;

    private volatile boolean flag;

    private final Map<String, String> cache = new HashMap<>();
    private final Map<String, String> cacheConvert = new HashMap<>();


    public HuffmanTree(String source) {
        this.source = source;


    }

    @Override
    public HuffmanNode<E> getRoot() {
        return (HuffmanNode<E>) super.getRoot();
    }

    @Override
    public HuffmanNode<E> addChild(Node<E> parent, E data, boolean left) {
        return null;
    }

    @Override
    public void checkNodeType(Node<E> parent) {
        if (!(parent instanceof HuffmanNode)) {
            throw new ClassCastException("该节点不是哈夫曼结点");
        }
    }


    /**
     * 进行编码
     */
    public String encode() {
        sb = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            search(source.charAt(i) + "", getRoot());
        }
        return sb.toString();
    }

    public String encodeCache() {
        sb = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            sb.append(cache.get(source.charAt(i) + ""));
        }
        return sb.toString();
    }

    private void search(String s, HuffmanNode<E> root) {
        if (root.left == null && root.right == null) {
            if (root.data.equals(s)) {
                sb.append(root.huffCode);
            }
        }
        if (root.left != null) {
            search(s, getLeft(root));
        }
        if (root.right != null) {
            search(s, getRight(root));
        }
    }

    public String toStringCode(String encode) {
        int start = 0;
        int end = 1;
        sb = new StringBuilder();
        while (end <= encode.length()) {
            String substring = encode.substring(start, end);
            flag = false;
            // 这个最垃圾
            //matches(substring, getRoot());
            matches(substring, getRoot(), 0);
            if (flag) {
                start = end;
            }
            // 这个最快
            //String s = cacheConvert.get(substring);
            //if (s != null) {
            //    //sb.append(s);
            //    length += s.length() * 8;
            //    start = end;
            //}
            end++;
        }
        return sb.toString();
    }

    private void matches(String str, HuffmanNode<E> root, int i) {
        if (root.left == null && root.right == null) {
            //sb.append(root.data);
            length += str.length() * 8;
            flag = true;
        }
        if (i == str.length()) {
            return;
        }
        matches(str, str.charAt(i) == '0' ? getLeft(root) : getRight(root), ++i);

    }


    private void matches(String str, HuffmanNode<E> root) {
        notQuick(str, root);
    }

    private void notQuick(String str, HuffmanNode<E> root) {
        if (flag) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (str.equals(root.huffCode)) {
                //sb.append(root.data);
                length += str.length() * 8;
                flag = true;
            }
        }
        if (root.left != null) {
            matches(str, getLeft(root));
        }
        if (root.right != null) {
            matches(str, getRight(root));
        }
    }

    public static class HuffmanNode<E> extends BinaryNode<E> implements Comparable<HuffmanNode<Integer>> {

        /**
         * 权值
         */
        int weight;

        /**
         * huffman 编码
         */
        String huffCode = "";

        public HuffmanNode(E data) {
            super(data);
        }

        public HuffmanNode(E data, BinaryNode<E> left, BinaryNode<E> right) {
            super(data, left, right);
        }

        public HuffmanNode(E data, int weight) {
            super(data);
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public int compareTo(@NotNull HuffmanTree.HuffmanNode<Integer> o) {
            return weight - o.weight;
        }

        @Override
        public String toString() {
            return "HuffmanNode{" +
                    "weight=" + weight +
                    ", huffCode='" + huffCode + '\'' +
                    ", data=" + data +
                    '}';
        }
    }


    @Override
    public HuffmanNode<E> getLeft(Node<E> parent) {
        return (HuffmanNode<E>) super.getLeft(parent);
    }

    @Override
    public HuffmanNode<E> getRight(Node<E> parent) {
        return (HuffmanNode<E>) super.getRight(parent);
    }

    /**
     * 初始化数据
     *
     * @return Halloween
     */
    public List<HuffmanNode<String>> initNodes() {
        Map<Character, Integer> tempCount = new HashMap<>();
        for (int i = 0; i < source.length(); i++) {
            tempCount.merge(source.charAt(i), 1, Integer::sum);
        }
        List<HuffmanNode<String>> result = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : tempCount.entrySet()) {
            result.add(new HuffmanNode<>(entry.getKey() + "", entry.getValue()));
        }
        return result;
    }

    /**
     * 构建 huffman 编码
     */
    public void buildHuffmanCode() {
        HuffmanNode<E> root = getRoot();
        doBuildHuffmanCode(root);
    }

    private void doBuildHuffmanCode(HuffmanNode<E> parent) {
        if (parent.left == null && parent.right == null) {
            cache.put(parent.data.toString(), parent.huffCode);
            cacheConvert.put(parent.huffCode, parent.data.toString());
        }
        HuffmanNode<E> left = this.getLeft(parent);
        if (left != null) {
            left.huffCode = parent.huffCode + "0";
            doBuildHuffmanCode(left);
        }
        HuffmanNode<E> right = this.getRight(parent);
        if (right != null) {
            right.huffCode = parent.huffCode + "1";
            doBuildHuffmanCode(right);
        }

    }


    /**
     * 根据指定的普通node节点集合构建哈夫曼树
     *
     * @param data node节点集合,采用普通list集合
     */
    public void build(List<HuffmanNode<E>> data) {
        while (data.size() > 1) {
            //手动对集合的节点按照权值大小从大到小进行排序
            data.sort(Comparator.comparing(HuffmanNode::getWeight));
            HuffmanNode<E> left = data.remove(0);
            HuffmanNode<E> right = data.remove(0);
            //生成新节点，新节点的权值为两个子节点的权值之和
            HuffmanNode<E> parent = new HuffmanNode<>(null, left.getWeight() + right.getWeight());
            parent.left = left;
            parent.right = right;
            data.add(parent);
        }
        //采用循环不断地执行上面的步骤，直到list集合中只剩下一个节点，最后剩下的这个节点就是哈夫曼树的根节点
        this.root = data.remove(0);
    }

    /**
     * 根据指定的最小堆构建哈夫曼树
     *
     * @param data node节点集合,采用最小堆
     */
    public void build(PriorityQueue<HuffmanNode<E>> data) {
        while (data.size() > 1) {
            //移除并获取权值最小的两个节点
            HuffmanNode<E> left = data.poll();
            HuffmanNode<E> right = data.poll();
            //生成新节点，新节点的权值为两个子节点的权值之和
            HuffmanNode<E> parent = new HuffmanNode<>(null, left.getWeight() + Objects.requireNonNull(right).getWeight());
            //让新节点作为两个权值最小节点的父节点
            parent.left = left;
            parent.right = right;
            //将新节点加入到最小堆中,自动排序
            data.add(parent);
        }
        //采用循环不断地执行上面的步骤，直到list集合中只剩下一个节点，最后剩下的这个节点就是哈夫曼树的根节点
        this.root = data.poll();
    }
}

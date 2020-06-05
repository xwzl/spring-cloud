package com.java.prepare.tree;

import lombok.Data;

/**
 * 双亲孩子树
 * <p>
 * 集合中
 *
 * @author xuweizhi
 * @since 2020/05/30 13:51
 */
@Data
public class ParentsTree<T> {

    /**
     * 数组中存放各个结点的具体信息：
     * <p>
     * 如果 firstNode 值为空，说明该结点为叶结点或者终端结点；
     * 否则，firstNode 存放的其下一层子节点的信息；一直往复循环，知道结点为终端结点为止。
     */
    private ChildBox<T>[] nodes;

    private int size = 10;

    public ParentsTree() {
        // 强转
        nodes = (ChildBox<T>[]) new ChildBox[size];
    }


    // 孩子结点
    @Data
    static class ChildNode {
        /**000
         * 孩子结点的下标
         */
        private int child;
        /**
         * 指向下一个孩子结点的指针
         */
        private ChildNode next;
    }

    // 表头结构
    @Data
    static class ChildBox<T> {
        // 存放在树中的结点的数据
        private Object data;
        // 存放双亲的下标
        private int parent;
        // 指向第一个孩子的指针
        private ChildNode firstNode;
    }

}


package com.java.prepare.search;

import lombok.Builder;
import lombok.Data;

/**
 * 平衡二叉树:
 * <p>
 * 如果我们需要查找的集合本身没有顺序，在频繁查找的同时也需要经常的插入和删除操作，显然我们需要构建一棵二叉排序树，但是不
 * 平衡的二叉排序树，查找效率是非常低的，因此我们需要在构建时，就让这棵二叉排序树是平衡二叉树，此时我们的查找时间复杂度就
 * 为O(logN)，而插入和删除也为O(logN)。这显然是比较理想的一种动态查找表算法。
 *
 * @author xuweizhi
 * @since 2020/06/04 21:55
 */
@Data
@Builder(toBuilder = true)
public class BiATree {

    /**
     * 数据
     */
    int data;

    /**
     * 结点的平衡因子
     */
    int bf;

    /**
     * 左子树
     */
    BiATree lChild;

    /**
     * 右子树
     */
    BiATree rChild;

    public BiATree() {
    }

    public BiATree(int data, BiATree lChild, BiATree rChild) {
        this.data = data;
        this.lChild = lChild;
        this.rChild = rChild;
    }

    public BiATree(int data, int bf, BiATree lChild, BiATree rChild) {
        this.data = data;
        this.bf = bf;
        this.lChild = lChild;
        this.rChild = rChild;
    }

    @Override
    public String toString() {
        return "[结点值:" + data + ", 平衡因子:" + bf + ", 左孩子:" + lChild + ", 右孩子:" + rChild + "]";
    }

}

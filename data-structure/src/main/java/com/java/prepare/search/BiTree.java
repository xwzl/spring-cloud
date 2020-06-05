package com.java.prepare.search;

import lombok.Builder;
import lombok.Data;

/**
 * 二叉树
 *
 * @author xuweizhi
 * @since 2020/06/04 21:55
 */
@Data
@Builder(toBuilder = true)
public class BiTree {

    /**
     * 数据
     */
    int data;

    /**
     * 左子树
     */
    protected BiTree lChild;

    /**
     * 右子树
     */
    protected BiTree rChild;

    public BiTree() {
    }

    public BiTree(int data, BiTree lChild, BiTree rChild) {
        this.data = data;
        this.lChild = lChild;
        this.rChild = rChild;
    }

}

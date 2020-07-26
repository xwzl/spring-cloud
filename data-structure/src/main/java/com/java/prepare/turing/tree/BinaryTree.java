package com.java.prepare.turing.tree;

/**
 * @author xuweizhi
 * @since 2020/07/25 13:13
 */
public interface BinaryTree<E> {

    /**
     * 获取根节点
     *
     * @return 根节点 ;或者null--表示空树
     */
    Node<E> getRoot();

    /**
     * 添加子节点
     *
     * @param parent 父节点的引用
     * @param data   节点数据
     * @param left   是否是左子节点,true 是;false 否
     */
    Node<E> addChild(Node<E> parent, E data, boolean left);

    /**
     * 获取左子节点
     *
     * @param parent 父节点引用
     * @return 左子节点或者null--表示没有左子节点
     */
    Node<E> getLeft(Node<E> parent);

    /**
     * 获取右子节点
     *
     * @param parent 父节点引用
     * @return 右子节点或者null--表示没有右子节点
     */
    Node<E> getRight(Node<E> parent);

    /**
     * 数据判空
     *
     * @param data 数据
     */
    void checkNullData(E data);

    /**
     * 父节点不能为空
     *
     * @param parent 父节点
     */
    void checkNullParent(Node<E> parent);

    /**
     * 检查节点类型
     *
     * @param parent 当前节点
     */
    void checkNodeType(Node<E> parent);

    /**
     * 是否是空树
     *
     * @return true 是 ;false 否
     */
    boolean isEmpty();

    /**
     * 树的节点数
     *
     * @return 结点树
     */
    int size();

    /**
     * 中序遍历,提供给外部使用的api
     *
     * @return 遍历的数据
     */
    String toInorderTraversalString();
}

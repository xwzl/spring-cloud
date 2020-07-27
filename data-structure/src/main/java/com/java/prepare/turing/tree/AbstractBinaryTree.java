package com.java.prepare.turing.tree;

import javax.el.MethodNotFoundException;
import java.util.*;

/**
 * @author xuweizhi
 * @since 2020/07/25 13:36
 */
public abstract class AbstractBinaryTree<E> implements BinaryTree<E>, SortTree<E> {

    protected int size;

    protected BinaryNode<E> root;

    private Comparator<? super E> cmp;

    List<BinaryNode<E>> str = new ArrayList<>();

    public AbstractBinaryTree() {
    }

    public AbstractBinaryTree(Comparator<? super E> cmp) {
        this.cmp = cmp;
    }

    @Override
    public BinaryNode<E> getRoot() {
        return root;
    }

    public Node<E> addChild(Node<E> parent, E data, boolean left) {
        throw new MethodNotFoundException("二叉排序排序外的接口必须实现");
    }

    public BinaryNode<E> getLeft(Node<E> parent) {
        return parent == null ? null : ((BinaryNode<E>) parent).left;
    }

    public BinaryNode<E> getRight(Node<E> parent) {
        return parent == null ? null : ((BinaryNode<E>) parent).right;
    }

    public void checkNullData(E data) {
        if (data == null) {
            throw new NullPointerException("该节点数据不能为空");
        }
    }

    public void checkNullParent(Node<E> parent) {
        if (parent == null) {
            throw new NoSuchElementException("父节点不能为空");
        }
        checkNodeType(parent);
    }

    /**
     * 判断当前节点类型
     *
     * @param parent 父节点
     */
    public abstract void checkNodeType(Node<E> parent);

    /**
     * 是否为空树
     */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public BinaryNode<E> findMin(BinaryNode<E> root) {
        if (root == null) {
            return null;
        } else if (root.left == null) {
            /*如果该节点没有左右子节点，那么该节点就是最小的节点，返回*/
            return root;
        }
        /*如果该节点存在左子节点，那么继续向左递归查找*/
        return findMin(root.left);
    }

    @Override
    public BinaryNode<E> findMax(BinaryNode<E> root) {
        if (root == null) {
            return null;
            /*如果该节点没有右子节点，那么该节点就是最大的节点，返回*/
        } else if (root.right == null) {
            return root;
        }
        /*如果该节点存在右子节点，那么继续向右递归查找*/
        return findMax(root.right);
    }


    @Override
    public void remove(E e) {
        throw new MethodNotFoundException("仅二叉排序树实现");
    }

    /**
     * 返回节点数
     *
     * @return 节点数
     */
    public int size() {
        return size;
    }

    @Override
    public String toInorderTraversalString() {
        //如果是空树,直接返回空
        if (isEmpty()) {
            return null;
        }
        //从根节点开始递归
        inorderTraversal(getRoot());
        //获取遍历结果
        String s = str.toString();
        str.clear();
        return s;
    }

    @Override
    public void insert(E e) {
        throw new NoSuchElementException("仅二叉排序树实现");
    }

    /**
     * 中序遍历 内部使用的递归遍历方法,借用了栈的结构
     *
     * @param root 节点,从根节点开始
     */
    private void inorderTraversal(BinaryNode<E> root) {
        BinaryNode<E> left = getLeft(root);
        if (left != null) {
            //如果左子节点不为null,则继续递归遍历该左子节点
            inorderTraversal(left);
        }
        //添加数据节点
        str.add(root);
        //获取节点的右子节点
        BinaryNode<E> right = getRight(root);
        if (right != null) {
            //如果右子节点不为null,则继续递归遍历该右子节点
            inorderTraversal(right);
        }
    }



    @Override
    public boolean contains(E e) {
        checkNullData(e);
        return contains(e, getRoot());
    }

    private boolean contains(E e, BinaryNode<E> root) {
        if (root == null) {
            return false;
        }
        int compare = compare(e, root.data);
        if (compare < 0) {
            /*如果小于0，则说明e<root.date 继续查询左子树*/
            return contains(e, getLeft(root));
        } else if (compare > 0) {
            /*如果大于0，则说明e>root.date 继续查询右子树*/
            return contains(e, getRight(root));
        } else {
            /*如果等于0，则说明e=root.date 即查询成功*/
            return true;
        }
    }

    /**
     * 对元素进行比较大小的方法,如果传递了自定义比较器,则使用自定义比较器,否则则需要数据类型实现Comparable接口
     *
     * @param e1 被比较的第一个对象
     * @param e2 被比较的第二个对象
     * @return 0 相等 ;小于0 e1 < e2 ;大于0 e1 > e2
     */
    protected int compare(E e1, E e2) {
        if (cmp == null) {
            // 这里还可以细化
            return (int) e1 - (int) e2;
        }
        return Objects.requireNonNull(cmp).compare(e1, e2);
    }

    public static class BinaryNode<E> implements Node<E> {
        public E data;
        public BinaryNode<E> left;
        public BinaryNode<E> right;

        public BinaryNode(E data) {
            this.data = data;
        }

        public BinaryNode(E data, BinaryNode<E> left, BinaryNode<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return data.toString();
        }


    }


}

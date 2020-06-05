package com.java.prepare.tree;

import lombok.Data;

/**
 * 线索二叉树
 * <p>
 * 在二叉树的结点上加上线索的二叉树称为线索二叉树，对二叉树以某种遍历方式（如先序、中序、后序或层次等）进行遍历，使其变为线索
 * 二叉树的过程称为对二叉树进行线索化。
 * <p>
 * 对于n个结点的二叉树，在二叉链存储结构中有n+1个空链域，利用这些空链域存放在某种遍历次序下该结点的前驱结点和后继结点的指针，
 * 这些指针称为线索，加上线索的二叉树称为线索二叉树。
 * <p>
 * 这种加上了线索的二叉链表称为线索链表，相应的二叉树称为线索二叉树(Threaded BinaryTree)。根据线索性质的不同，线索二叉树可
 * 分为前序线索二叉树、中序线索二叉树和后序线索二叉树三种。
 * <p>
 * 注意：线索链表解决了无法直接找到该结点在某种遍历序列中的前驱和后继结点的问题，解决了二叉链表找左、右孩子困难的问题。
 * <h1>本质</h1>
 * 二叉树的遍历本质上是将一个复杂的非线性结构转换为线性结构，使每个结点都有了唯一前驱和后继（第一个结点无前驱，最后一个结点无后
 * 继）。对于二叉树的一个结点，查找其左右子女是方便的，其前驱后继只有在遍历中得到。为了容易找到前驱和后继，有两种方法。一是在结
 * 点结构中增加向前和向后的指针，这种方法增加了存储开销，不可取；二是利用二叉树的空链指针。
 * <p>
 * 优势与不足编辑
 * 优势
 * (1)利用线索二叉树进行中序遍历时，不必采用堆栈处理，速度较一般二叉树的遍历速度快，且节约存储空间。
 * (2)任意一个结点都能直接找到它的前驱和后继结点。 [2]
 * 不足
 * (1)结点的插入和删除麻烦，且速度也较慢。
 * (2)线索子树不能共用。
 *
 * @author xuweizhi
 * @since 2020/05/30 18:10
 */
@Data
public class ThreadBinaryTree<T> {

    /**
     * 数据结点
     */
    private T data;

    /**
     * 当 lTag 和 rTag 为 0 时，leftChild 和 rightChild 分别是指向左孩子和右孩子的指针；
     * 否则，leftChild是指向结点前驱的线索(pre)，rightChild 是指向结点的后继线索(suc)。
     * 由于标志只占用一个二进位，每个结点所需要的存储空间节省很多。
     */
    private ThreadBinaryTree<T> lChild, rChild;

    /**
     * 上一次访问或者最近的一个节点
     */
    private static ThreadBinaryTree preNode;

    /**
     * lTag: 为 0 时指向该结点的左孩子，为 1 时指向该节点的前驱
     * rTag: 为 0 时指向该结点的有孩子，为 1 时指向该节点的后继
     */
    private int lTag, rTag;

    public ThreadBinaryTree() {
        // 默认有左右孩子
        this.lTag = 0;
        this.rTag = 0;
    }

    /**
     * 创建一颗二叉树，约定用户按照前序遍历的方式输入数据
     *
     * @param tree 树
     */
    public static <T> ThreadBinaryTree<T> createTree(T[] datas) {
        if (TreeUtil.index >= datas.length) {
            return null;
        }
        T data = datas[TreeUtil.index];
        TreeUtil.index++;

        if (data.equals(" ")) {
            return null;
        }
        ThreadBinaryTree<T> temp = new ThreadBinaryTree<>();
        temp.setData(data);
        // 创建左子树
        ThreadBinaryTree<T> lTree = createTree(datas);
        if (lTree != null) {
            temp.setLChild(lTree);
        } /*else {
            temp.setLTag(1);
        }*/
        // 创建右子树
        ThreadBinaryTree<T> rTree = createTree(datas);
        if (rTree != null) {
            temp.setRChild(rTree);
        } /*else {
            temp.setRTag(1);
        }*/
        return temp;
    }

    /**
     * 访问二叉树结点的具体操作
     */
    public static <T> void visit(T t, int level) {
        System.out.printf(level + ":" + t + " ");
    }

    /**
     * 中序遍历建立线索二叉树
     */
    public static <T> void inThreading(ThreadBinaryTree<T> tree, int level) {
        if (tree != null) {
            // 中序
            inThreading(tree.getLChild(), level + 1);
            visit(tree.getData(), level);
            // 如果该节点没有左孩子，设置 lTag 设置 1,并把 LChild 设置为刚刚访问的结点
            if (tree.getLChild() == null) {
                tree.setLTag(1);
                tree.setLChild(preNode);
            }
            // 如果右孩子为空，设置 tree 为 preNode 结点的后继结点
            if (preNode.getRChild() == null) {
                preNode.setRTag(1);
                preNode.setRChild(tree);
            }
            // 初始化
            preNode = tree;
            inThreading(tree.getRChild(), level + 1);
        }
    }

    /**
     * 加上头结点，遍历线索二叉树
     */
    private static <T> void inOrderTravcerse(ThreadBinaryTree<T> tree) {
        ThreadBinaryTree<T> temp= tree.getLChild();
        int level = 0;
         while (temp != tree) {
            while (temp.getLTag() == 0) {
                temp = temp.getLChild();
            }
            visit(temp.getData(), level + 1);
            while (temp.getRTag() == 1 && temp.getRChild() != tree) {
                temp = temp.getRChild();
                visit(temp.getData(), level + 1);
            }
            temp = temp.getRChild();
        }
    }

    public static <T> ThreadBinaryTree<T> inOrderThreading(ThreadBinaryTree<T> data) {
        // 头结点
        ThreadBinaryTree<T> p = new ThreadBinaryTree<>();
        // 如果树为空，哈哈哈
        if (data == null) {
            p.setLChild(p);
        } else {
            p.setLChild(data);
            preNode = p;
            inThreading(data, 0);
            preNode.setRChild(p);
            preNode.setRTag(1);
            p.setRChild(preNode);
        }
        return p;
    }


    public static void main(String[] args) {
        ThreadBinaryTree<String> tree = createTree(new String[]{"A", "B", "C", " ", " ", "D", "E", " ", " ", "F", " ", " ", "B1", "C1", " ", " ", "D1"});
        //reorderTraversal(tree, 0);
        ThreadBinaryTree<String> headPointer = inOrderThreading(tree);
        System.out.println("");
        inOrderTravcerse(headPointer);
        //System.out.println("");
    }
}

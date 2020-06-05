package com.java.prepare.search.tree;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 首先节点对象还是需要一个数据域和两个引用域，相比于二叉排序树，还要多一个节点高度的字段，这样方便计算平衡因子，并且提
 * 供返回节点高度的方法。
 * <p>
 * 另外还需要一个比较器的引用，因为需要对元素进行排序，自然需要比较元素的大小，如果外部传递了比较器，那么就使用用户指定
 * 的比较器进行比较，否则，数据类型E必须是Comparable接口的子类，否则因为不能比较而报错。
 * <p>
 * 另外，还需要提供中序遍历的方法，该遍历方法对于二叉排序树的结果将会顺序展示。
 *
 * @author xuweizhi
 * @since 2020/06/05 22:26
 */
@Slf4j
public class AvlTree<E> {

    /**
     * 外部保存根节点的引用
     */
    private BinaryTreeNode<E> root;

    /**
     * 自定义比较器
     */
    private Comparator<? super E> cmp;


    /**
     * 树节点的数量
     */
    private int size;

    /**
     * 内部节点对象
     *
     * @param <E> 数据类型
     */
    public static class BinaryTreeNode<E> {

        //数据域
        E data;

        //左子节点
        BinaryTreeNode<E> left;

        //右子节点
        BinaryTreeNode<E> right;

        //节点高度 从0开始,从下往上;null节点高度返回-1
        int height;

        public BinaryTreeNode(E data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString();
        }

    }

    /**
     * 指定比较器
     *
     * @param cmp 比较器
     */
    public AvlTree(Comparator<? super E> cmp) {
        this.cmp = cmp;
    }

    /**
     * 空构造器
     */
    public AvlTree() {
    }

    /**
     * 是否是空树
     *
     * @return true 是 ;false 否
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回节点数
     *
     * @return 节点数
     */
    public int size() {
        return size;
    }


    /**
     * 对元素进行比较大小的方法,如果传递了自定义比较器,则使用自定义比较器,否则则需要数据类型实现Comparable接口
     *
     * @param e1 被比较的第一个对象
     * @param e2 被比较的第二个对象
     * @return 0 相等 ;小于0 e1 < e2 ;大于0 e1 > e2
     */
    private int compare(E e1, E e2) {
        if (cmp != null) {
            return cmp.compare(e1, e2);
        } else {
            return ((Comparable<E>) e1).compareTo(e2);
        }
    }


    /**
     * 保存遍历出来的节点数据
     */
    List<BinaryTreeNode<E>> str = new ArrayList<>();

    /**
     * 中序遍历,提供给外部使用的api
     *
     * @return 遍历的数据
     */
    public String toInorderTraversalString() {

        //如果是空树,直接返回空
        if (isEmpty()) {
            return null;
        }
        //从根节点开始递归
        inorderTraversal(root);
        //获取遍历结果
        String s = str.toString();
        str.clear();
        return s;
    }

    /**
     * 中序遍历 内部使用的递归遍历方法,借用了栈的结构
     *
     * @param root 节点,从根节点开始
     */
    private void inorderTraversal(BinaryTreeNode<E> root) {
        BinaryTreeNode<E> left = getLeft(root);
        if (left != null) {
            //如果左子节点不为null,则继续递归遍历该左子节点
            inorderTraversal(left);
        }
        //添加数据节点
        str.add(root);
        //获取节点的右子节点
        BinaryTreeNode<E> right = getRight(root);
        if (right != null) {
            //如果右子节点不为null,则继续递归遍历该右子节点
            inorderTraversal(right);
        }
    }

    /**
     * 获取左子节点
     *
     * @param parent 父节点引用
     * @return 左子节点或者null--表示没有左子节点
     */
    public BinaryTreeNode<E> getLeft(BinaryTreeNode<E> parent) {
        return parent == null ? null : parent.left;
    }

    /**
     * 获取右子节点
     *
     * @param parent 父节点引用
     * @return 右子节点或者null--表示没有右子节点
     */
    public BinaryTreeNode<E> getRight(BinaryTreeNode<E> parent) {
        return parent == null ? null : parent.right;
    }

    /**
     * 获取根节点
     *
     * @return 根节点 ;或者null--表示空树
     */
    public BinaryTreeNode<E> getRoot() {
        return root;
    }

    /**
     * 获取height
     *
     * @param node 节点
     * @return 高度或者-1 表示节点为null
     */
    private int getHeight(BinaryTreeNode<E> node) {
        return node == null ? -1 : node.height;
    }

    /**
     * 查找,开放给外部使用的api
     * <p>
     * 平衡二叉树就是一颗二叉排序树，其查找方法可以复用二叉排序树的查找方法，很简单：
     *
     * <ul>
     *     <li>若根节点的关键字值等于查找的关键字，成功，返回true；</li>
     *     <li>否则，若小于根节点的关键字值，递归查左子树；</li>
     *     <li>若大于根节点的关键字值，递归查右子树；</li>
     *     <li>最终查找到叶子节点还是没有数据，那么查找失败，则返回false</li>
     * </ul>
     *
     * @param e 要查找的元素
     * @return false 不存在 true 存在
     */
    public boolean contains(E e) {
        return contains(e, root);
    }

    /**
     * 查找,内部调用的方法,从根节点开始查找
     *
     * @param e    要查找的元素
     * @param root 节点
     * @return false 不存在 true 存在
     */
    private boolean contains(E e, BinaryTreeNode<E> root) {
        /*null校验*/
        if (root == null) {
            return false;
        }
        /*调用比较的方法*/
        int i = compare(e, root.data);
        /*如果大于0，则说明e>root.date 继续查询右子树*/
        if (i > 0) {
            return contains(e, root.right);
            /*如果小于0，则说明e<root.date 继续查询左子树*/
        } else if (i < 0) {
            return contains(e, root.left);
        } else {
            /*如果等于0，则说明e=root.date 即查询成功*/
            return true;
        }
    }

    /**
     * 很简单，只需要递归的查看所有节点，判断是否存在的节点的左右子节点高度差绝对值是否大于1的情况就能判断了，如果存在，
     * 那么返回false表示不是平衡二叉树，不存在就返回true表示是平衡二叉树。
     * <p>
     * 保存是否平衡的标志
     */
    private boolean balance = true;

    /**
     * 检查是否是平衡二叉树的方法,当然也可以debug看,如果你不嫌麻烦……
     *
     * @return true 是 ；false 否
     */
    public boolean checkBalance() {
        checkBalance(root);
        boolean balanceNow = balance;
        balance = true;
        return balanceNow;
    }

    /**
     * 递归检查是否平衡,实际上这里采用了后序遍历,即左子节点-右子节点-根节点的方法递归遍历检查
     *
     * @param root 根节点
     * @return 节点的高度
     */
    private int checkBalance(BinaryTreeNode<E> root) {
        if (root == null) {
            return -1;
        }
        //返回左子树的高度
        int hl = checkBalance(root.left);
        //返回右子树的高度
        int hr = checkBalance(root.right);
        //如果root的左右子树高度差绝对值大于1,或者checkBalance和getHeight方法获取的左/右子树高度不一致,那么算作不平衡
        if (Math.abs(getHeight(root.left) - getHeight(root.right)) > 1 ||
                getHeight(root.left) != hl || getHeight(root.right) != hr) {
            balance = false;
        }
        return getHeight(root);
    }
    /**
     * 插入的方法
     *
     * 平衡二叉树和二叉排序树的最大区别就是在插入和删除的时候了。我们已经讨论过插入之后的4种出现平衡问题的
     * 特殊情况，这里不再赘述。
     *
     */

    /**
     * 插入,开放给外部使用的api
     *
     * @param e 要插入的元素
     */
    public void insert(E e) {
        //返回root,但此时新的节点可能已经被插入进去了
        root = insert(e, root);
    }

    /**
     * 插入,开放给外部使用的api
     *
     * @param es 要插入的元素的数组,注意,数组元素的顺序存储的位置将会影响二叉排序树的生成
     */
    public void insert(E[] es) {
        //返回root,但此时新的节点可能已经被插入进去了
        for (E e : es) {
            root = insert(e, root);
        }

    }

    /**
     * 插入,内部调用的方法,先从根节点开始递归查找要插入的位置,然后插入
     * 大部分代码都和排序二叉树的相似，区别就是在插入之后，会调用尝试重平衡的方法rebalance
     *
     * @param e    要插入的数据
     * @param root 节点
     * @return 原节点重平衡之后的节点或者新插入的节点
     */
    private BinaryTreeNode<E> insert(E e, BinaryTreeNode<E> root) {
        /*没有查找到,那么直接构建新的节点返回,将会在上一层方法中被赋值给其父节点的某个引用,这个插入的位置肯定是该遍历路径上的最后一点
         * 即插入的元素节点肯定是属于叶子节点*/
        if (root == null) {
            size++;
            return new BinaryTreeNode<>(e);
        }
        /*调用比较的方法*/
        int i = compare(e, root.data);
        /*如果大于0，则说明e>root.date 继续查询右子树*/
        if (i > 0) {
            //重新赋值
            root.right = insert(e, root.right);
            /*如果小于0，则说明e<root.date 继续查询左子树*/
        } else if (i < 0) {
            //重新赋值
            root.left = insert(e, root.left);
        } else {
            /*如果等于0，则说明e=root.date 即存在节点 什么都不做*/
            log.info(e.toString() + " 该节点已存在");
        }
        /*insert递归插入之后,在返回时,会调用重新平衡并且设置高度的方法 尝试重平衡root根节点  而不是像排序二叉树一样简单的返回root
         *从新插入节点的父节点一直向上回溯直到根节点,尝试寻找最小不平衡树,找到之后会进行平衡,返回返回平衡之后的树,.*/
        return reBalance(root);
    }

    /**
     * 重平衡的方法
     * 1)	在节点X的左孩子节点的左子树中插入元素，简称LL 右旋
     * 2)	在节点X的左孩子节点的右子树中插入元素，简称LR 左-右双旋
     * 3)	在节点X的右孩子节点的左子树中插入元素，简称RL 左旋
     * 4)	在节点X的右孩子节点的右子树中插入元素，简称RR 右-左双旋
     *
     * @param root 树的根节点,无论是否是最小不平衡树,都是走这个方法
     * @return 平衡之后的树的根节点
     */
    private BinaryTreeNode<E> reBalance(BinaryTreeNode<E> root) {
        /*1、如果节点为null,直接返回null*/
        if (root == null) {
            return null;
        }
        /*2、开始旋转*/
        /*2.1、如果左子树的高度减去右子树的高度值大于1,说明左子树的高度大于右子树的高度至少2层,可能是情况1、2 继续判断*/
        if (getHeight(root.left) - getHeight(root.right) > 1) {
            /*如果左子节点的左子节点高度大于左子节点的右子节点高度,那说明是情况1,否则是情况2*/
            if (getHeight(root.left.left) >= getHeight(root.left.right)) {
                /*2.1.1、右旋*/
                root = rotateRight(root);
            } else {
                /*2.1.2、左-右双旋*/
                root = rotateLeftAndRight(root);
            }
            /*2.2、如果右子树的高度减去左子树的高度值大于1,说明右子树的高度大于左子树的高度至少2层,可能是情况3、4 继续判断*/
        } else if (getHeight(root.right) - getHeight(root.left) > 1) {
            /*如果右子节点的右子节点高度大于右子节点的左子节点高度,那说明是情况4,否则是情况3*/
            if (getHeight(root.right.right) >= getHeight(root.right.left)) {
                /*2.2.1、左旋*/
                root = rotateLeft(root);
            } else {
                /*2.2.2、右-左双旋*/
                root = rotateRightAndLeft(root);
            }
        }
        /*3、到这一步,说明旋转完毕,或者不需要旋转,但是都需要重新计算高度,高度为左/右子树高度最大值+1*/
        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        return root;
    }


    /**
     * 右旋
     * 通解:右旋之后，k2成为根节点，k1成为k2的右子节点，k2的右子树2成为k1的左子树
     * C
     * B
     * A
     * <p>
     * temp = c.left; //b
     * c.left = temp.left;
     * temp.right = c;
     *
     * @param k1 需要旋转的最小不平衡树根节点
     * @return k2 旋转后的最小不平衡树根节点, 已经转换为平衡二叉树
     */
    private BinaryTreeNode<E> rotateRight(BinaryTreeNode<E> k1) {
        //获取k2,k2是k1的左子节点
        BinaryTreeNode<E> k2 = k1.left;
        //k2的右子树成为k1的左子树
        k1.left = k2.right;
        //k1成为k2的右子节点
        k2.right = k1;
        //k1的高度等于k1的左或者右子树的高度的最大值+1;
        k1.height = Math.max(getHeight(k1.left), getHeight(k1.right)) + 1;
        //k2的高度等于k2的左子节点和k1的高度(此时k1就是k2的右子节点)的最大值+1
        k2.height = Math.max(getHeight(k2.left), k1.height) + 1;
        //返回k2,k2成为根节点
        return k2;
    }

    /**
     * 左旋 很简单,实际上就是右旋的镜像
     * 通解:左旋之后，k2成为根节点，k1成为k2的左子节点，k2的左子树2成为k1的右子树
     *
     * @param k1 需要旋转的最小不平衡树根节点
     * @return k2 旋转后的最小不平衡树根节点, 已经转换为平衡二叉树
     */
    private BinaryTreeNode<E> rotateLeft(BinaryTreeNode<E> k1) {
        //获取k2,k2是k1的右子节点
        BinaryTreeNode<E> k2 = k1.right;
        //k2的左子树成为k1的右子树
        k1.right = k2.left;
        //k1成为k2的左子节点
        k2.left = k1;
        //k1的高度等于k1的左或者右子树的高度的最大值+1;
        k1.height = Math.max(getHeight(k1.left), getHeight(k1.right)) + 1;
        //k2的高度等于k2的右子节点和k1的高度(此时k1就是k2的左子节点)的最大值+1
        k2.height = Math.max(getHeight(k2.right), k1.height) + 1;
        //返回k2,k2成为根节点
        return k2;
    }

    /**
     * 右-左双旋
     * 通解：将k3当作新的根节点，并且先使得k2右旋成为k3的右子树，然后k1左旋成为k3的左子树，并且左子树2成为k1的右子树，右子树2成为k2的左子树
     *
     * @param k1 需要旋转的最小不平衡树根节点
     * @return 旋转后的最小不平衡树根节点, 已经转换为平衡二叉树
     */
    private BinaryTreeNode<E> rotateRightAndLeft(BinaryTreeNode<E> k1) {
        /*1、先对k1的右子节点k2进行右旋,返回右旋之后的根节点k3,然后使得成为k3成为的k1的左子树*/
        k1.right = rotateRight(k1.right);
        /*2、然后对k1进行左旋,成为k3的左子树,返回的根节点就是k3,即返回旋转之后的根节点*/
        return rotateLeft(k1);
    }

    /**
     * 左-右双旋 很简单,实际上就是右-左双旋的镜像
     * 通解: 将k3当作新的根节点，并且先使得k2左旋成为k3的左子树，然后k1右旋成为k3的右子树，并且左子树2成为k2的右子树，右子树2成为k1的左子树
     *
     * @param k1 需要旋转的最小不平衡树根节点
     * @return 旋转后的最小不平衡树根节点, 已经转换为平衡二叉树
     */
    private BinaryTreeNode<E> rotateLeftAndRight(BinaryTreeNode<E> k1) {
        /*1、先对k1的左子节点k2进行左旋,返回左旋之后的根节点k3,然后使得成为k3成为的k1的左子树*/
        k1.left = rotateLeft(k1.left);
        /*2、然后对k1进行右旋,成为k3的右子树,返回的根节点就是k3,即返回旋转之后的根节点*/
        return rotateRight(k1);
    }


    /**
     * 查找最小的节点
     *
     * @param root 根节点
     * @return 最小的节点
     */
    private BinaryTreeNode<E> findMin(BinaryTreeNode<E> root) {
        if (root == null) {
            return null;
            /*如果该节点没有左右子节点，那么该节点就是最小的节点，返回*/
        } else if (root.left == null) {
            return root;
        }
        /*如果该节点存在左子节点，那么继续向左递归查找*/
        return findMin(root.left);
    }

    /**
     * 查找最大的节点
     *
     * @param root 根节点
     * @return 最大的节点
     */
    private BinaryTreeNode<E> findMax(BinaryTreeNode<E> root) {
        if (root == null) {
            return null;
            /*如果该节点没有右子节点，那么该节点就是最大的节点，返回*/
        } else if (root.right == null) {
            return root;
        }
        /*如果该节点存在右子节点，那么继续向右递归查找*/
        return findMax(root.right);
    }

    /**
     * 删除,开放给外部使用的api
     * <p>
     * 平衡二叉树节点的删除同样可能导致产生不平衡的状态，因此同样在二叉排序树的删除代码的基础上，删除元素之后需要在删除节
     * 点之上进行回溯直到根节点，尝试找出最小不平衡树来进行重平衡。其平衡的方法是和插入的时候是一样的。
     *
     * @param e 要删除的元素
     */
    public void delete(E e) {
        //返回root,但此时可能有一个节点已经被删除了
        root = delete(e, root);
    }

    /**
     * 删除,内部调用的方法,删除分为三种情况: 1、该节点没有子节点 2、该字节仅有一个子节点 3、该节点具有两个子节点
     *
     * @param e    要删除的数据
     * @param root (子)树根节点
     * @return 该根节点重平衡之后的节点
     */
    private BinaryTreeNode<E> delete(E e, BinaryTreeNode<E> root) {
        /*没有查找到,那么什么都不做*/
        if (root == null) {
            return null;
        }
        /*调用比较的方法*/
        int i = compare(e, root.data);
        /*如果大于0，则说明e>root.date 继续查询右子树*/
        if (i > 0) {
            //重新赋值
            root.right = delete(e, root.right);
            /*如果小于0，则说明e<root.date 继续查询左子树*/
        } else if (i < 0) {
            //重新赋值
            root.left = delete(e, root.left);
        } else {
            /*如果等于0，则说明e=root.date 即查询成功 开始执行删除*/
            /*如果两个子节点都不为null*/
            if (root.left != null && root.right != null) {
                /*递归查找最小的节点，然后递归删除*/
                root.data = findMin(root.right).data;
                root.right = delete(root.data, root.right);
            } else {
                /*如果一个子节点不为null，则返回该子节点；或者两个子节点都为null，则返回null
                 * 此时该root节点已经被"绕过了"*/
                root = (root.left != null) ? root.left : root.right;
                --size;
            }
        }
        /*和二叉排序树直接返回节点不同的是,删除操作完成之后将会调用该方法,从被删除节点回溯至根节点,对节点进行重平衡,然后才返回平衡后的节点*/
        return reBalance(root);
    }


}


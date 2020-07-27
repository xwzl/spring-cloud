package com.java.prepare.turing.tree;

/**
 * 平衡二叉树
 *
 * @author xuweizhi
 * @since 2020/07/27 18:47
 */
public class AvlBinaryTree<E> extends AbstractBinaryTree<E> implements BinaryTree<E>, SortTree<E> {

    /**
     * 保存是否平衡的标志
     */
    private boolean balance = true;

    /**
     * 检查是否是平衡二叉树的方法,当然也可以debug看,如果你不嫌麻烦……
     *
     * @return true 是 ；false 否
     */
    public boolean checkBalance() {
        checkBalance(getRoot());
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
    private int checkBalance(AvlNode<E> root) {
        if (root == null) {
            return -1;
        }
        //返回左子树的高度
        int hl = checkBalance(getLeft(root));
        //返回右子树的高度
        int hr = checkBalance(getRight(root));
        //如果root的左右子树高度差绝对值大于1,或者checkBalance和getHeight方法获取的左/右子树高度不一致,那么算作不平衡
        if (Math.abs(getHeight(getLeft(root)) - getHeight(getRight(root))) > 1 ||
                getHeight(getLeft(root)) != hl || getHeight(getRight(root)) != hr) {
            balance = false;
        }
        return getHeight(root);
    }

    /**
     * 插入,开放给外部使用的api
     *
     * @param e 要插入的元素
     */
    public void insert(E e) {
        //返回root,但此时新的节点可能已经被插入进去了
        root = insert(e, getRoot());
    }

    /**
     * 插入,开放给外部使用的api
     *
     * @param es 要插入的元素的数组,注意,数组元素的顺序存储的位置将会影响二叉排序树的生成
     */
    public void insert(E[] es) {
        //返回root,但此时新的节点可能已经被插入进去了
        for (E e : es) {
            root = insert(e, getRoot());
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
    private AvlNode<E> insert(E e, AvlNode<E> root) {
        /*没有查找到,那么直接构建新的节点返回,将会在上一层方法中被赋值给其父节点的某个引用,这个插入的位置肯定是该遍历路径上的最后一点
         * 即插入的元素节点肯定是属于叶子节点*/
        if (root == null) {
            size++;
            return new AvlNode<>(e);
        }
        /*调用比较的方法*/
        int i = compare(e, root.data);
        /*如果大于0，则说明e>root.date 继续查询右子树*/
        if (i > 0) {
            //重新赋值
            root.right = insert(e, getRight(root));
            /*如果小于0，则说明e<root.date 继续查询左子树*/
        } else if (i < 0) {
            //重新赋值
            root.left = insert(e, getLeft(root));
        } else {
            /*如果等于0，则说明e=root.date 即存在节点 什么都不做*/
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
    private AvlNode<E> reBalance(AvlNode<E> root) {
        /*1、如果节点为null,直接返回null*/
        if (root == null) {
            return null;
        }
        /*2、开始旋转*/
        /*2.1、如果左子树的高度减去右子树的高度值大于1,说明左子树的高度大于右子树的高度至少2层,可能是情况1、2 继续判断*/
        if (getHeight(getLeft(root)) - getHeight(getRight(root)) > 1) {
            /*如果左子节点的左子节点高度大于左子节点的右子节点高度,那说明是情况1,否则是情况2*/
            if (getHeight(getLeft(getLeft(root))) >= getHeight(getLeft(getRight(root)))) {
                /*2.1.1、右旋*/
                root = rotateRight(root);
            } else {
                /*2.1.2、左-右双旋*/
                root = rotateLeftAndRight(root);
            }
            /*2.2、如果右子树的高度减去左子树的高度值大于1,说明右子树的高度大于左子树的高度至少2层,可能是情况3、4 继续判断*/
        } else if (getHeight(getRight(root)) - getHeight(getLeft(root)) > 1) {
            /*如果右子节点的右子节点高度大于右子节点的左子节点高度,那说明是情况4,否则是情况3*/
            if (getHeight(getRight(getRight(root))) >= getHeight(getRight(getLeft(root)))) {
                /*2.2.1、左旋*/
                root = rotateLeft(root);
            } else {
                /*2.2.2、右-左双旋*/
                root = rotateRightAndLeft(root);
            }
        }
        /*3、到这一步,说明旋转完毕,或者不需要旋转,但是都需要重新计算高度,高度为左/右子树高度最大值+1*/
        root.height = Math.max(getHeight(getLeft(root)), getHeight(getLeft(root))) + 1;
        return root;
    }


    /**
     * 右旋
     * 通解:右旋之后，k2成为根节点，k1成为k2的右子节点，k2的右子树2成为k1的左子树
     *
     * @param k1 需要旋转的最小不平衡树根节点
     * @return k2 旋转后的最小不平衡树根节点, 已经转换为平衡二叉树
     */
    private AvlNode<E> rotateRight(AvlNode<E> k1) {
        //获取k2,k2是k1的左子节点
        AvlNode<E> k2 = getLeft(k1);
        //k2的右子树成为k1的左子树
        k1.left = getRight(k2);
        //k1成为k2的右子节点
        k2.right = k1;
        //k1的高度等于k1的左或者右子树的高度的最大值+1;
        k1.height = Math.max(getHeight(getLeft(k1)), getHeight(getRight(k1))) + 1;
        //k2的高度等于k2的左子节点和k1的高度(此时k1就是k2的右子节点)的最大值+1
        k2.height = Math.max(getHeight(getLeft(k2)), k1.height) + 1;
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
    private AvlNode<E> rotateLeft(AvlNode<E> k1) {
        //获取k2,k2是k1的右子节点
        AvlNode<E> k2 = getRight(k1);
        //k2的左子树成为k1的右子树
        k1.right = getLeft(k2);
        //k1成为k2的左子节点
        k2.left = k1;
        //k1的高度等于k1的左或者右子树的高度的最大值+1;
        k1.height = Math.max(getHeight(getLeft(k1)), getHeight(getRight(k1))) + 1;
        //k2的高度等于k2的右子节点和k1的高度(此时k1就是k2的左子节点)的最大值+1
        k2.height = Math.max(getHeight(getRight(k2)), k1.height) + 1;
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
    private AvlNode<E> rotateRightAndLeft(AvlNode<E> k1) {
        /*1、先对k1的右子节点k2进行右旋,返回右旋之后的根节点k3,然后使得成为k3成为的k1的左子树*/
        k1.right = rotateRight(getRight(k1));
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
    private AvlNode<E> rotateLeftAndRight(AvlNode<E> k1) {
        /*1、先对k1的左子节点k2进行左旋,返回左旋之后的根节点k3,然后使得成为k3成为的k1的左子树*/
        k1.left = rotateLeft(getLeft(k1));
        /*2、然后对k1进行右旋,成为k3的右子树,返回的根节点就是k3,即返回旋转之后的根节点*/
        return rotateRight(k1);
    }

    /**
     * 删除,开放给外部使用的api
     *
     * @param e 要删除的元素
     */
    public void delete(E e) {
        //返回root,但此时可能有一个节点已经被删除了
        root = delete(e, getRoot());
    }

    /**
     * 删除,内部调用的方法,删除分为三种情况: 1、该节点没有子节点 2、该字节仅有一个子节点 3、该节点具有两个子节点
     *
     * @param e    要删除的数据
     * @param root (子)树根节点
     * @return 该根节点重平衡之后的节点
     */
    private AvlNode<E> delete(E e, AvlNode<E> root) {
        /*没有查找到,那么什么都不做*/
        if (root == null) {
            return null;
        }
        /*调用比较的方法*/
        int i = compare(e, root.data);
        /*如果大于0，则说明e>root.date 继续查询右子树*/
        if (i > 0) {
            //重新赋值
            root.right = delete(e, getRight(root));
            /*如果小于0，则说明e<root.date 继续查询左子树*/
        } else if (i < 0) {
            //重新赋值
            root.left = delete(e, getLeft(root));
        } else {
            /*如果等于0，则说明e=root.date 即查询成功 开始执行删除*/
            /*如果两个子节点都不为null*/
            if (root.left != null && root.right != null) {
                /*递归查找最小的节点，然后递归删除*/
                root.data = findMin(root.right).data;
                root.right = delete(root.data, getRight(root));
            } else {
                /*如果一个子节点不为null，则返回该子节点；或者两个子节点都为null，则返回null
                 * 此时该root节点已经被"绕过了"*/
                root = (root.left != null) ? getLeft(root) : getRight(root);
                --size;
            }
        }
        /*和二叉排序树直接返回节点不同的是,删除操作完成之后将会调用该方法,从被删除节点回溯至根节点,对节点进行重平衡,然后才返回平衡后的节点*/
        return reBalance(root);
    }

    /**
     * 获取当前结点高度
     *
     * @param avlNode avl 高度
     * @return avl 结点高度
     */
    public int getHeight(AvlNode<E> avlNode) {
        return avlNode == null ? -1 : avlNode.height;
    }

    @Override
    public AvlNode<E> getRoot() {
        return (AvlNode<E>) super.getRoot();
    }

    @Override
    public AvlNode<E> getLeft(Node<E> parent) {
        return (AvlNode<E>) super.getLeft(parent);
    }

    @Override
    public AvlNode<E> getRight(Node<E> parent) {
        return (AvlNode<E>) super.getRight(parent);
    }

    @Override
    public void checkNodeType(Node<E> parent) {
        if (!(parent instanceof AvlNode)) {
            throw new ClassCastException("该节点不属于平衡二叉排序树节点");
        }
    }

    static class AvlNode<E> extends BinaryNode<E> {
        private int height;

        public AvlNode(E data) {
            super(data);
        }
    }
}

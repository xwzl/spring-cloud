package com.java.prepare.search.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 二叉排序树:
 * 假设查找的数据集是普通的顺序存储，那么插入操作就是将记录放在表的末端，给表记录数加一即可，删除操作
 * 可以是删除后，后面的记录向前移，也可以是要删除的元素与最后一个元素互换，表记录数减一，反正整个数据
 * 集也没有什么顺序，这样的效率也不错。应该说，插入和删除对于顺序存储结构来说，效率是可以接受的，但这
 * 样的表由于无序造成查找的效率很低。
 * <p>
 * 如果查找的数据集是有序线性表，并且是顺序存储的，查找可以用折半、插值、斐波那契等查找算法来实现，可惜，
 * 因为有序，在插入和删除操作上，为了维持顺序，需要移动大量元素，就需要耗费大量的时间。
 * <p>
 * 有没有一种即可以使得插入和删除效率不错，又可以比较高效率地实现查找的算法呢？这就是二叉排序树。
 * <p>
 * 二叉排序树（Binary Sort Tree），又称为二叉查找树/二叉搜索树（binary search tree）。它是具有下列
 * 性质的二叉树：
 * <ul>
 *     <li>若它的左子树不空，则左子树上所有节点的值均小于它的根结构的值；</li>
 *     <li>若它的右子树不空，则右子树上所有节点的值均大于它的根节点的值；</li>
 *     <li>它的左、右子树也分别为二叉排序树；</li>
 *     <li>二叉排序树也可以是一个空树。</li>
 * </ul>
 * 构造一棵二叉排序树的目的，其主要目的并不是为了排序，而是为了提高查找和插入删除关键字的速度，用以提升数
 * 据结构的综合能力。不管怎么说，在一个有序数据集上的查找，速度总是要快于无序的数据集的，而二叉排序树这种
 * 非线性的结构，也有利于插入和删除的实现。
 *
 * @author xuweizhi
 * @since 2020/06/05 21:55
 */
public class BinarySearchTree<E> {

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
     * 首先，最简单的节点对象还是需要一个数据域和两个引用域。
     * <p>
     * 另外还需要一个比较器的引用，因为需要对元素进行排序，自然需要比较元素的大小，如果外部传递了比
     * 较器，那么就使用用户指定的比较器进行比较，否则，数据类型E必须是Comparable接口的子类，否则因
     * 为不能比较而报错。
     * <p>
     * 另外，还需要提供中序遍历的方法，该遍历方法对于二叉排序树的结果将会顺序展示。
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
    public BinarySearchTree(Comparator<? super E> cmp) {
        this.cmp = cmp;
    }

    /**
     * 空构造器
     */
    public BinarySearchTree() {
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
     * 查找,开放给外部使用的api
     *
     * <ul>
     *     <li>若根节点的关键字值等于查找的关键字，成功，返回true；</li>
     *     <li>否则，若小于根节点的关键字值，递归查左子树；</li>
     *     <li>若大于根节点的关键字值，递归查右子树；</li>
     *     <li>最终查找到叶子节点还是没有数据，那么查找失败，则返回false。</li>
     * </ul>
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
     * <h3>插入的方法</h3>
     * 有了二叉排序树的查找函数，那么所谓的二叉排序树的插入，其实也就是将关键字放到树中的合适位置
     * 而已，插入操作就好像在调用查找的操作，如果找到了那么什么都不做，返回false，如果没找到，则
     * 将要插入的元素插入到遍历的路径的最后一点上：
     *
     * <ul>
     *     <li>若二叉树为空。则单独生成根节点，返回true。</li>
     *     <li>执行查找算法，找出被插节点的父亲节点。</li>
     *     <li>判断被插节点是其父亲节点的左、右儿子。将被插节点作为叶子节点插入，返回true。如果原
     *     节点存在那么什么都不做，返回false。注意：新插入的节点总是叶子节点。</li>
     * </ul>
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
     *
     * @param e    要插入的数据
     * @param root 节点
     * @return 原节点或者新插入的节点
     */
    private BinaryTreeNode<E> insert(E e, BinaryTreeNode<E> root) {
        // 没有查找到, 那么直接构建新的节点返回, 将会在上一层方法中被赋值给其父节点的某个引用,
        // 这个插入的位置肯定是该遍历路径上的最后一点 即插入的元素节点肯定是属于叶子节点
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
        }
        //没查询到最底层,则返回该节点
        return root;
    }


    /**
     * 查找最小的节点:
     * 很简单，最左边的节点一定是最小的，最右边的节点一定是最大的。因此查找最小的节点只需要向左
     * 递归查找，查找最大的节点只需要向右递归查找。
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
     * 删除的方法：
     * <p>
     * 对于二叉排序树的删除，就不是那么容易，我们不能因为删除了节点，而让这棵树变得不满足二叉
     * 排序树的特性，所以删除需要考虑多种情况。一共有三种情况需要考虑：
     * <ul>
     *     <li>如果查找到的将要被删除的节点没有子节点，那么很简单，直接删除父节点对于该节点的引用即可。</li>
     *     <li>如果查找到的将要被删除的节点具有一个子节点，那么也很简单，直接绕过该节点将原本父节点对于
     *     该节点的引用指向该节点的子节点即可</li>
     *     <li>如果查找到的将要被删除的节点具有两个子节点，那么就比较麻烦了:
     *     一般我们选择使用右子树的最小节点替代要删除的节点，因为右子树的最小节点不可能有左儿子，所以第二次delete要更加容易
     *     </li>
     * </ul>
     * <p>
     * 删除,开放给外部使用的api
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
     * @param root 根节点
     * @return 该节点
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
            //从新赋值
            root.right = delete(e, root.right);
            /*如果小于0，则说明e<root.date 继续查询左子树*/
        } else if (i < 0) {
            //从新赋值
            root.left = delete(e, root.left);
        } else {
            /*如果等于0，则说明e=root.date 即查询成功 开始执行删除*/
            /*如果两个子节点都不为null*/
            if (root.left != null && root.right != null) {
                /*方法1、递归查找最小的节点，然后递归删除  该方法不推荐使用*/
                //root.data = findMin(root.right).data;
                //root.right = delete(root.data, root.right);
                /*方法2、递归查找并删除最小的节点 推荐*/
                root.data = findAndDeleteMin(root.right, root);
            } else {
                /*如果一个子节点不为null，则返回该子节点；或者两个子节点都为null，则返回null
                 * 此时该root节点已经被"绕过了"*/
                root = (root.left != null) ? root.left : root.right;
            }
            size--;
        }
        //没查询到最底层,则返回该节点
        return root;
    }

    /**
     * 查找最小的节点并删除
     * 最小的节点肯定不存在两个字节点,有可能没有子节点,有可能存在右子节点
     *
     * @param root   节点
     * @param parent 节点的父节点
     * @return 被删除的最小的节点
     */
    private E findAndDeleteMin(BinaryTreeNode<E> root, BinaryTreeNode<E> parent) {
        //如果节点为null，返回
        if (root == null) {
            return null;
            /*如果节点的左子节点为null,那么该节点就是最小的节点*/
            /*1、该最小节点肯定没有左子节点，因为左子节点比父节点小，但是可能有右子节点*/
            /*2、此时该节点可能作为某个节点的左子节点，也可能作为原父节点的右子节点（即右子树是一颗右斜树），这里需要分开讨论*/
        } else if (root.left == null) {
            //如果该节点是父节点的右子节点,说明还没进行递归或者第一次递归就找到了最小节点.
            //那么此时,应该让该节点的父节点的右子节点指向该节点的右子节点,并返回该节点的数据，该节点由于没有了强引用，会被GC删除
            if (root == parent.right) {
                parent.right = root.right; // root.right == null
            } else {
                //如果该节点不是父节点的右子节点，说明进行了多次递归。
                //那么此时,应该让该节点的父节点的左子节点指向该节点的右子节点,并返回该节点的数据，该节点由于没有了强引用，会被GC删除
                parent.left = root.right;
            }
            //返回最小节点的数据
            return root.data;
        }
        //递归调用,注意此时是往左查找
        return findAndDeleteMin(root.left, root);
    }


}


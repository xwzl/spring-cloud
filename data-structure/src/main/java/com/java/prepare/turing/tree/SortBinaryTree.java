package com.java.prepare.turing.tree;

import java.util.Comparator;
import java.util.Objects;

/**
 * <h2>二叉排序树</h2>
 * <h3>类架构</h3>
 * <ul>
 *     <li>首先，最简单的节点对象还是需要一个数据域和两个引用域。</li>
 *     <li>另外还需要一个比较器的引用，因为需要对元素进行排序，自然需要比较元素的大小，如果外部传递了比较器，那么就使用用户指定的比较器
 *     进行比较，否则，数据类型E必须是Comparable接口的子类，否则因为不能比较而报错。</li>
 *     <li>另外，还需要提供中序遍历的方法，该遍历方法对于二叉排序树的结果将会顺序展示。</li>
 * </ul>
 *
 *
 * <h3>查找的方法</h3>
 * <p>
 * 查找的方法很简单：
 *
 * <ul>
 *     <li>若根节点的关键字值等于查找的关键字，成功，返回true；</li>
 *     <li>否则，若小于根节点的关键字值，递归查左子树；</li>
 *     <li>若大于根节点的关键字值，递归查右子树；</li>
 *     <li>最终查找到叶子节点还是没有数据，那么查找失败，则返回false。</li>
 * </ul>
 *
 * <h3>插入的方法</h3>
 * 有了二叉排序树的查找函数，那么所谓的二叉排序树的插入，其实也就是将关键字放到树中的合适位置而已，插入操作就好像在调用查找的操作，如果找到
 * 了那么什么都不做，返回false，如果没找到，则将要插入的元素插入到遍历的路径的最后一点上：
 *
 * <ul>
 *     <ul>若二叉树为空。则单独生成根节点，返回true。</ul>
 *     <ul>执行查找算法，找出被插节点的父亲节点。</ul>
 *     <ul>判断被插节点是其父亲节点的左、右儿子。将被插节点作为叶子节点插入，返回true。如果原节点存在那么什么都不做，返回false。注意：新插
 *     入的节点总是叶子节点。</ul>
 * </ul>
 *
 * <h3>删除的方法</h3>
 * 对于二叉排序树的删除，就不是那么容易，我们不能因为删除了节点，而让这棵树变得不满足二叉排序树的特性，所以删除需要考虑多种情况。一共有三种情况需要考虑：
 * <ul>
 *     <li>如果查找到的将要被删除的节点没有子节点，那么很简单，直接删除父节点对于该节点的引用即可</li>
 *     <li>如果查找到的将要被删除的节点具有一个子节点，那么也很简单，直接绕过该节点将原本父节点对于该节点的引用指向该节点的子节点即可</li>
 *     <li>如果查找到的将要被删除的节点具有两个子节点，那么就比较麻烦了</li>
 *     <li>比如我们需要删除73，那么我们就必须要找出一个已存在的能够替代73的节点，然后删除该节点。实际上该73节点的左子树的最大节点62和右子树的最小节点77
 *     都能够替代73节点，即它们正好是二叉排序树中比它小或比它大的最接近73的两个数</li>
 *     <li>一般我们选择使用右子树的最小节点替代要删除的节点，因为右子树的最小节点不可能有左儿子，所以第二次delete要更加容易</li>
 * </ul>
 *
 * <h2>二叉排序树的总结</h2>
 * 总之，二叉排序树是以链接的方式存储，保持了链接存储结构在执行插入或删除操作时不用移动元素的优点，只要找到合适的插入和删除位置后，仅需修改链接指针即可。
 * <p>
 * 插入删除的时间性能比较好。而对于二叉排序树的查找，走的就是从根节点到要查找的节点的路径，其比较次数等于给定值的节点在二叉排序树的层数。极端情况，最少为
 * 1次，即根节点就是要找的节点，最多也不会超过树的深度。也就是说，二叉排序树的查找性能取决于二叉排序树的形状。可问题就在于，二叉排序树的形状是不确定的。
 * <p>
 * 也就是说，我们希望二叉排序树是比较平衡的，即其深度与完全二叉树相同，均为|log2n+1|(|x|表示不大于x的最大整数)，那么查找的时间复杂也就为O(logn)，近似于
 * 折半查找，事实上，上图的左图也不够平衡，明显的左重右轻。而极端情况下的右图,则完全退化成为链表，查找的时间复杂度为O(n)，这等同于顺序查找。
 * <p>
 * 因此，如果我们希望对一个集合按二叉排序树查找，最好是把它构建成一棵平衡的二叉排序树，防止极端情况的发生。这样我们就引申出另一个问题，如何让二叉排序树平
 * 衡的问题。关于这个问题，在后续的平衡二叉树（AVL树）的部分将会详细解释！
 *
 * @author xuweizhi
 * @since 2020/07/26 11:41
 */
public class SortBinaryTree<E> extends AbstractBinaryTree<E> implements BinaryTree<E> {

    /**
     * 自定义比较器
     */
    private Comparator<? super E> cmp;

    public SortBinaryTree() {
    }

    public SortBinaryTree(Comparator<? super E> cmp) {
        this.cmp = cmp;
    }

    @Override
    public SortNode<E> getRoot() {
        return (SortNode<E>) super.getRoot();
    }

    @Override
    public SortNode<E> getLeft(Node<E> parent) {
        return (SortNode<E>) super.getLeft(parent);
    }

    @Override
    public SortNode<E> getRight(Node<E> parent) {
        return (SortNode<E>) super.getRight(parent);
    }

    @Override
    public void checkNodeType(Node<E> parent) {
        if (!(parent instanceof SortNode)) {
            throw new ClassCastException("该节点不属于二叉排序树节点");
        }
    }

    /**
     * 外部插叙 api
     *
     * @param e 关键字
     * @return true 有值
     */
    @Override
    public boolean contains(E e) {
        checkNullData(e);
        return contains(e, getRoot());
    }

    private boolean contains(E e, SortNode<E> root) {
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

    @Override
    public void insert(E e) {
        checkNullData(e);
        //返回root,但此时新的节点可能已经被插入进去了
        root = insert(e, getRoot());
    }

    /**
     * 插入,内部调用的方法,先从根节点开始递归查找要插入的位置,然后插入
     *
     * @param e    要插入的数据
     * @param root 节点
     * @return 原节点或者新插入的节点
     */
    private SortNode<E> insert(E e, SortNode<E> root) {
        //没有查找到,那么直接构建新的节点返回,将会在上一层方法中被赋值给其父节点的某个引用,
        //这个插入的位置肯定是该遍历路径上的最后一点,即插入的元素节点肯定是属于叶子节点
        if (root == null) {
            size++;
            return new SortNode<>(e);
        }
        // 插入的数据与当前结点比较
        int compare = compare(e, root.data);
        // 如果插入的结点比当前结点小，则插入当前结点的左边
        if (compare < 0) {
            root.left = insert(e, getLeft(root));
        } else if (compare > 0) {
            root.right = insert(e, getRight(root));
        } else {
            // 相等则说明已经被插入
            System.out.println("当前结点已插入：" + e);
        }
        // 没有查询到底层，返回该节点
        return root;
    }

    @Override
    public void remove(E e) {
        //返回root,但此时可能有一个节点已经被删除了
        root = delete(e, getRoot());
    }

    /**
     * 删除,内部调用的方法,删除分为三种情况:
     * 1、该节点没有子节点
     * 2、该字节仅有一个子节点
     * 3、该节点具有两个子节点
     *
     * @param e    要删除的数据
     * @param root 根节点
     * @return 该节点
     */
    private SortNode<E> delete(E e, SortNode<E> root) {
        /*没有查找到,那么什么都不做*/
        if (root == null) {
            return null;
        }
        /*调用比较的方法*/
        int i = compare(e, root.data);
        /*如果大于0，则说明e>root.date 继续查询右子树*/
        if (i > 0) {
            //从新赋值
            root.right = delete(e, getRight(root));
            /*如果小于0，则说明e<root.date 继续查询左子树*/
        } else if (i < 0) {
            //从新赋值
            root.left = delete(e, getLeft(root));
        } else {
            /*如果等于0，则说明e=root.date 即查询成功 开始执行删除*/
            /*如果两个子节点都不为null*/
            if (root.left != null && root.right != null) {
                /*方法1、递归查找最小的节点，然后递归删除  该方法不推荐使用*/
                //root.data = findMin(root.right).data;
                //root.right = delete(root.data, root.right);
                /*方法2、递归查找并删除最小的节点 推荐*/
                root.data = findAndDeleteMin(getRight(root), root);
            } else {
                /*如果一个子节点不为null，则返回该子节点；或者两个子节点都为null，则返回null
                 * 此时该root节点已经被"绕过了"*/
                root = (getLeft(root) != null) ? getLeft(root) : getRight(root);
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
    private E findAndDeleteMin(SortNode<E> root, SortNode<E> parent) {
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
                parent.right = root.right;
            } else {
                //如果该节点不是父节点的右子节点，说明进行了多次递归。
                //那么此时,应该让该节点的父节点的左子节点指向该节点的右子节点,并返回该节点的数据，该节点由于没有了强引用，会被GC删除
                parent.left = root.right;
            }
            //返回最小节点的数据
            return root.data;
        }
        //递归调用,注意此时是往左查找
        return findAndDeleteMin(getLeft(root), root);
    }


    /**
     * 对元素进行比较大小的方法,如果传递了自定义比较器,则使用自定义比较器,否则则需要数据类型实现Comparable接口
     *
     * @param e1 被比较的第一个对象
     * @param e2 被比较的第二个对象
     * @return 0 相等 ;小于0 e1 < e2 ;大于0 e1 > e2
     */
    private int compare(E e1, E e2) {
        if (cmp == null) {
            // 这里还可以细化
            return (int) e1 - (int) e2;
        }
        return Objects.requireNonNull(cmp).compare(e1, e2);
    }

    public static class SortNode<E> extends BinaryNode<E> {

        public SortNode(E data) {
            super(data);
        }

        public SortNode(E data, BinaryNode<E> left, BinaryNode<E> right) {
            super(data, left, right);
        }
    }


}

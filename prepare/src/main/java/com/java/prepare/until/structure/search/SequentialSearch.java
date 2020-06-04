package com.java.prepare.until.structure.search;

/**
 * 顺序查找：https://blog.csdn.net/smile_from_2015/article/details/72190562?utm_source=gold_browser_extension
 *
 * @author xuweizhi
 * @since 2020/06/04 10:16
 */
public class SequentialSearch {
    private final static int SIZE = 15;

    private final static int[] f = new int[SIZE];

    /**
     * 全局变量，存放查找的关键字所在的父节点
     */
    private static BiTree parentNode = new BiTree();

    private static BiTree newTree = new BiTree();


    /**
     * 顺序查找，a 为数组，n 为要查找的数组个数，key 要查找的关键字
     *
     * @param data 数据
     * @param n    查找范围
     * @param t    指定值
     * @param <T>  关键字
     * @return 返回匹配下标
     */
    public static <T> int search(T[] data, int n, T t) {
        for (int i = 0; i < n; i++) {
            if (data[i] == t) {
                return i;
            }
        }
        return 0;
    }

    /**
     * search 方法不够完美，因为每次循环时都需要对 i 是否越界，即是否小于等于 n 作判断。
     * <p>
     * 事实上非常简单，每次我们可以设置一个哨兵，可以每次让 i 与 n 做比较。
     * <p>
     * 顺序查找技术有很大的缺点，n 很大时，查找效率极为低下。
     *
     * @param data 数据
     * @param n    查找范围
     * @param t    指定值
     * @param <T>  关键字
     * @return 返回匹配下标
     */
    public static <T> int searchMajor(T[] data, int n, T t) {
        int i;
        data[0] = t;// 哨兵
        i = n;
        // 哨兵免去了在查找过程中每一次比较后都要判断查找位置是否越界的小技巧，看似与原先差别
        // 不大，但在总数据较多时，效率提高很大，是非常号的编码技巧
        while (data[i] != t) {
            i--;
        }
        return i;
    }

    /**
     * 有序查找：二分查找
     * 折半查找(Binary Search)技术,又称为二分查找。它的前提是线性表中的记录
     * 必须是关键码有序(通常从小到大有序),线性表必须采用顺序存储。折半查找的基
     * 本思想是:在有序表中,取中间记录作为比较对象,若给定值与中间记录的关键字相
     * 等,则查找成功;若给定值小于中间记录的关键字,则在中间记录的左半区继续查找;
     * 若给定值大于中间记录的关键字,则在中间记录的右半区继续查找。不断重复上述过
     * 程，直到查找成功，或所有查找区域无记录，查找失败为止。
     *
     * @param data   数据
     * @param n      查找范围
     * @param target 指定值
     * @return 返回匹配下标
     */
    public static int binarySearch(int[] data, int n, int target) {
        int end = n;
        int begin = 0;
        while (begin <= end) {
            int temp = (begin + end) / 2;
            if (target > data[temp]) {
                begin = temp + 1;
            } else if (target < data[temp]) {
                end = temp - 1;
            } else if (target == data[temp]) {
                return temp;
            }
        }
        return 0;
    }

    /**
     * 有序查找：斐波那契查找法
     * <p>
     * 它是利用黄金分割原理来实现的。
     *
     * @param a   数组
     * @param n   下标
     * @param key 词
     * @return 结果
     */
    public static int fibonacciSearch(int[] a, int n, int key) {
        int low;
        int high;
        int mid;
        int k;
        low = 1; // 定义最低下标为记录首位
        high = n; // 定义最高下标为记录末位
        k = 0;
        initFibonacci();
        while (n > f[k] - 1) k++; // 计算 n 位于斐波那契数列的位置
        //for (int i = n; i < f[k] - 1; i++) a[i] = a[n]; // 将不满的数值补全
        int[] temp = new int[f[k]];
        for (int i = 0; i < f[k]; i++) {
            if (i > n) {
                temp[i] = a[n];
            } else {
                temp[i] = a[i];
            }
        }
        // 将不满的数值补全
        while (low <= high) {
            // 斐波那契最要是在这里节省了一部分计算机性能 加减优于乘除的性能
            mid = low + f[k - 1] - 1; // 计算当前分割下标
            if (key < temp[mid]) { // 若查找记录小于当前分隔记录
                high = mid - 1; // 最高下标调整到分隔下标 mid-1 处
                k = k - 1;// 斐波那契数列下标减一位
            } else if (key > temp[mid]) {
                low = mid + 1;
                k = k - 2;
            } else {
                if (mid < n) {
                    return mid; // 若相等则说明 mid 即为查找到的位置
                }
                return n; // 若 mid > n 说明是补全数值，返回 n
            }
        }
        return -1;
    }

    public static int fibonacciInit(int n) {
        return n <= 1 ? Math.max(0, n) : fibonacciInit(n - 1) + fibonacciInit(n - 2);
    }

    public static void initFibonacci() {
        for (int i = 0; i < SIZE; i++) {
            f[i] = fibonacciInit(i);
        }
    }

    /*
     总之,二叉排序树是以链接的方式存储，保持了链接存储结构在执行插入或删除操作时不用移动元素的优点，
     只要找到合造的插入和删除位置后，仅需修改链接指针即可。
     */

    /**
     * 二叉排序树查找（Binary Sort Tree）
     * <p>
     * 递归查找二叉排序树 T 中是否存在 key，f 指向 t 的双亲，其初始调用值为 Null,若查找成功，则
     * root 指向该数据元素结点，并返回 true;否则 p 指向查找路径上访问的最后一个节点并 返回 false
     *
     * @param t      待查询的二叉排序树
     * @param key    查找关键字
     * @param parent 指向 t 的双亲，默认初始值为 null
     * @return 查找成功返回为 true, 并将树结点赋值给全局变量 parentNode,查找失败返回 false
     */
    public static boolean searchBST(BiTree t, int key, BiTree parent) {
        if (t == null || t.data == 0) { //树节点不存在返回
            parentNode = parent; // 返回父节点
            return false;
        } else if (key == t.data) { // 匹配成功
            parentNode = parent; // 返回父节点，用于插入或者删除
            return true;
        } else if (key < t.data) { //关键字小于根节点查找左子树
            return searchBST(t.lChild, key, t);
        } else {// 关键字大于根节点查找右子树
            return searchBST(t.rChild, key, t);
        }
    }

    /**
     * 在二叉树中插入关键字key
     *
     * @param bt  二叉排序树
     * @param key 插入的关键字
     * @return 插入成功返回true，失败返回false
     */
    public static boolean insertBinaryTree(BiTree bt, int key) {
        BiTree binaryTree;
        if (!searchBST(bt, key, null)) {
            binaryTree = new BiTree();
            binaryTree.data = key;
            binaryTree.lChild = binaryTree.rChild = null;
            if (null == parentNode) {// 不存在，证明是父节点，将binaryTree指向bt成为新的根节点
                bt = binaryTree;
            } else if (key < parentNode.data) { // 当key小于子根节点，插入为左孩子
                parentNode.lChild = binaryTree;
            } else { // 当key大于子根节点，插入为右孩子
                parentNode.rChild = binaryTree;
            }
            preOrderTraverse(bt);
            return true;
        } else {
            System.out.println("该节点已存在");
        }
        return false;
    }


    /**
     * 生成二叉树
     *
     * @param key 关键字
     */
    public static void generateBinaryTree(int key) {
        BiTree binaryTree;
        if (!searchBST(newTree, key, null)) {
            binaryTree = new BiTree();
            binaryTree.data = key;
            binaryTree.lChild = binaryTree.rChild = null;
            if (null == parentNode) {// 不存在，证明是父节点，将binaryTree指向bt成为新的根节点
                newTree = binaryTree;
            } else if (key < parentNode.data) { // 当key小于子根节点，插入为左孩子
                parentNode.lChild = binaryTree;
            } else { // 当key大于子根节点，插入为右孩子
                parentNode.rChild = binaryTree;
            }
            preOrderTraverse(newTree);
        } else {
            System.out.println("该节点已存在");
        }
    }

    /**
     * 测试生成二叉树
     */
    public static void generateBinaryTree() {
        int[] a = {62, 88, 58, 47, 35, 73, 51, 99, 37, 93};
        for (int i = 0; i < a.length; i++) {
            System.out.println("第" + i + "次");
            generateBinaryTree(a[i]);
        }
        insertBinaryTree(newTree, 55);
        insertBinaryTree(newTree, 55);
        insertBinaryTree(newTree, 55);
        deleteBST(newTree, 55);
        deleteBST(newTree, 55);
        deleteBST(newTree, 55);

    }

    /**
     * 中序遍历打印线索二叉树
     *
     * @param t 当前结点
     */
    static void preOrderTraverse(BiTree t) {
        if (null == t || t.data == 0) {
            return;
        }
        if (t.lChild != null) {
            preOrderTraverse(t.lChild); // 中序遍历左子树
        }
        if (t.data != 0) {
            System.out.println("[" + t.data + "]"); // 显示当前节点的数据
        }
        if (t.rChild != null) {
            preOrderTraverse(t.rChild); // 最后遍历右子树
        }
    }

    /**
     * 根据我们对删除结点三种情况的分析：
     * <ul>
     *     <li>叶子结点</li>
     *     <li>仅有左或右子树的结点</li>
     *     <li>左右子树都有的及诶单</li>
     * </ul>
     *
     * @param t   当前结点
     * @param key 关键字
     * @return 返回
     */
    public static boolean deleteBST(BiTree t, int key) {
        if (!searchBST(t, key, null)) { // 若删除的结点不存在
            return false;
        } else {
            if (t.data == key) {
                return delete(t);
            } else if (key < t.data) {
                return deleteBST(t.lChild, key);
            } else {
                return deleteBST(t.rChild, key);
            }
        }
    }

    /**
     * 从二叉树排序中删除结点 p ,并重接它的左或右子树
     *
     * @param t 当前结点
     * @return 返回值
     */
    private static boolean delete(BiTree t) {
        BiTree q, s;
        if (null == t.rChild) {
            t = t.lChild; // 右子树为空则只需重接左子树
        } else if (null == t.lChild) {
            t = t.rChild; //// 左子树为空则只需重接右子树
        } else {
            q = t;
            s = t.lChild;
            // 转左，然后向右到尽头(找到待删结点前驱)
            while (null != s.rChild) {
                q = s;
                s = s.rChild;
            }
            t.data = s.data;// s指向被删除结点的直接前驱
            if (q != t) {//
                q.rChild = s.lChild;// 重接q的右子树
            } else {// q.data == bt.data，则说明s.rchild == null
                q.lChild = s.lChild; // 重接q的左子树
            }
        }
        return true;
    }





}

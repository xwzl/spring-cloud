package com.java.prepare.search;

/**
 * @author xuweizhi
 * @since 2020/06/04 23:54
 */
public class BiATreeTest {

    static final int LH = 1;// 左高
    static final int EH = 0;// 等高
    static final int RH = -1;// 右高
    /**
     * 用于存放各个方法中返回的临时树结点
     */
    static BiATree temp = new BiATree();

    /**
     * 右旋（顺时针）
     * 当传入一个二叉排序树bt，将它的左孩子结点定义为l，将l的右子树变成bt的左子树，再将 bt成l的右子树，
     * 最后将l替换bt成为根结点，这样就完成了一次右旋操作。
     *
     * @param bt 二叉排序树
     * @return 旋转
     */
    static BiATree rRotate(BiATree bt) {
        BiATree l;
        l = bt.lChild;//l指向bt的左子树的根节点
        bt.lChild = l.rChild;//l的右子树指向bt的左子树
        l.rChild = bt;//bt指向l的右子树
        bt = l;//l指向新的根节点
        return bt;
    }

    /**
     * 左旋（逆时针）
     */
    static BiATree lRotate(BiATree bt) {
        BiATree r;
        r = bt.rChild;
        bt.rChild = r.lChild;
        r.lChild = bt;
        bt = r;
        return bt;
    }


    /**
     * 现在我们来看左平衡旋转处理的函数代码,处理完成后返回平衡二叉树bt.
     *
     * 函数被调用，传入一个需调整平衡性的子树 bt。由于 LeftBalance 函数被调用时，其实已经确认当前
     * 子树时不平衡状态，且左子树的高度大于右子树的高度。换句话说，此时 bt 的根节点应该是平衡因子BF
     * 的值大于 1 的数。
     *
     * @param bt 待处理的左重右轻的二叉树
     */
    static BiATree leftBalance(BiATree bt) {
        BiATree l, lr;
        l = bt.lChild;// l指向bt的左子树根节点
        // 检查左子树的平衡度，并作相应平衡处理
        /* 修改bt及其左孩子的平衡因子 */
        switch (l.bf) {
            /* 新结点插在bt左孩子的左子树上，需要进行右旋处理 */
            case LH -> {
                bt.bf = l.bf = EH;
                temp = rRotate(bt);
                bt = temp;
            }
            /* 新结点插在bt的左孩子的右子树上，需要作双旋处理 */
            case RH -> {
                lr = l.rChild;/* lr指向bt左孩子的右子树根 */
                switch (lr.bf) {
                    case LH -> {
                        bt.bf = LH;
                        l.bf = EH;
                    }
                    case EH -> bt.bf = l.bf = EH;
                    case RH -> {
                        bt.bf = EH;
                        l.bf = LH;
                    }
                }
                lr.bf = EH;
                temp = lRotate(bt.lChild);/* 对bt的左孩子作左旋平衡处理 */
                rRotate(temp);/* 对bt作右旋平衡处理 */
                bt = temp;
            }
        }
        return bt;
    }


    /**
     * 处理完成后返回平衡二叉树
     *
     * @param bt 左轻右重的平衡二叉树
     */
    static BiATree rightBalance(BiATree bt) {
        BiATree r, rr;
        r = bt.rChild;// r指向bt的右子树根节点
        // 检查右子树的平衡度，并作相应平衡处理
        /* 修改bt及其左孩子的平衡因子 */
        switch (r.bf) {
            /* 新结点插在bt右孩子的右子树上，需要进行左旋处理 */
            case RH -> {
                bt.bf = r.bf = EH;
                temp = lRotate(bt);
                bt = temp;
            }
            /* 新结点输入在bt的右孩子的左子树上，需要作双旋处理 */
            case LH -> {
                rr = r.lChild;/* rr指向bt右孩子的左子树根 */
                switch (rr.bf) {
                    case LH -> {
                        bt.bf = RH;
                        r.bf = EH;
                    }
                    case EH -> bt.bf = r.bf = EH;
                    case RH -> {
                        bt.bf = LH;
                        r.bf = EH;
                    }
                }
                rr.bf = EH;
                temp = rRotate(bt.rChild);/* 对bt的右孩子作右旋平衡处理 */
                bt.rChild = temp;
                temp = lRotate(bt);/* 对bt作左旋平衡处理 */
                bt = temp;
            }
        }
        return bt;
    }

    /**
     * 全局变量,用于标识树是否增高
     */
    static boolean taller = false;

    /**
     * 在平衡二叉树t中若不存在与e有相同关键字的结点则插入t中并返回 t，若插入e后使得t失去平衡则需要作平衡处理
     *
     * @param t 平衡二叉排序树
     * @param e 待插入元素
     */
    static BiATree insertAVL(BiATree t, int e) {
        if (null == t) {
            // 插入新结点。树长高，taller为true
            t = new BiATree();
            t.data = e;
            t.lChild = t.rChild = null;
            t.bf = EH;
            taller = true;
        } else {
            if (e == t.data) {
                // 树中已存在和e有相同关键字的结点则不再插入
                taller = false;
                return t;
            } else if (e < t.data) {
                // 当e小于根结点或子根节点则应继续在t的左子树中进行搜索
                t.lChild = insertAVL(t.lChild, e);
                if (!taller)
                    return t;
                // 已插入到t的左子树中且左子树"长高"
                // 检查t的平衡度
                switch (t.bf) {
                    case LH -> {
                        // 原本左子树比右子树高，需要作左平衡处理
                        temp = leftBalance(t);
                        t = temp;
                        taller = false;
                    }
                    case EH -> {
                        // 原本左右子树等高，现因左子树增高而树增高
                        t.bf = LH;
                        taller = true;
                    }
                    case RH -> {
                        // 原本右子树比左子树高，现左右子树等高
                        t.bf = EH;
                        taller = false;
                    }
                }
            } else {// 应继续在t的右子树中进行搜索.
                t.rChild = insertAVL(t.rChild, e);
                if (!taller)
                    return t;
                // 已插入到 t的右子树中且右子树"长高"
                switch (t.bf) {
                    // 检查t的平衡度
                    case LH:
                        // 原本左子树比右子树高，现左右子树等高
                        t.bf = EH;
                        taller = false;
                        break;
                    case EH:
                        // 原本左右子树等高，现因右子树增高而树增高
                        t.bf = RH;
                        taller = true;
                        break;
                    case RH:
                        // 原本右子树比左子树高，需要作右平衡处理
                        temp = rightBalance(t);
                        t = temp;
                        taller = false;
                        break;
                    default:
                        break;
                }
            }
        }
        return t;
    }

    public static void main(String[] args) {
        int[] a = {3, 2, 1, 4, 5, 6, 7, 10, 9, 8};
        BiATree t = null;
        for (int value : a) {
            t = insertAVL(t, value);
            System.out.println(t + "\n");
        }
    }
}

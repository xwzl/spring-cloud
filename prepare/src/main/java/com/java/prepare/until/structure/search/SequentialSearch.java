package com.java.prepare.until.structure.search;

import org.junit.Test;

/**
 * 顺序查找
 *
 * @author xuweizhi
 * @since 2020/06/04 10:16
 */
public class SequentialSearch {
    private final static int SIZE = 15;

    private final static int[] f = new int[SIZE];


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

    @Test
    public void testFibonacci() {
        int[] a = {0, 1, 16, 24, 35, 47, 59, 62, 73, 88, 99};
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.println(fibonacciSearch(a, j, a[i]));
            }
        }

    }

    public static int fibonacciInit(int n) {
        return n <= 1 ? Math.max(0, n) : fibonacciInit(n - 1) + fibonacciInit(n - 2);
    }

    public static void initFibonacci() {
        for (int i = 0; i < SIZE; i++) {
            f[i] = fibonacciInit(i);
        }
    }
}

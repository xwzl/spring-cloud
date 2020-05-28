package com.java.prepare.until.structure.recursive;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 递归
 *
 * @author xuweizhi
 * @since 2020/05/28 9:55
 */
@Slf4j
public class Recursive {

    /**
     * 斐波那契迭代实现
     */
    private void fbiIteration(int size) {
        switch (size) {
            case 1:
            case 2:
                fbiPrint(size);
                break;
            default:
                int[] temp = new int[size];
                temp[0] = 1;
                temp[1] = 2;
                for (int i = 2; i < size; i++) {
                    temp[i] = temp[i - 1] + temp[i - 2];
                }
                fbiPrint(temp[size - 1]);
        }

    }

    private void fbiPrint(int result) {
        log.info("斐波那契输出结果：{}", result);
    }

    /**
     * 斐波那契递归实现
     *
     * 对比两种斐波那契的代码，迭代和递归的区别是：迭代使用的是循环结构，递归使用的是选择结构。
     *
     * 使用递归能使程序的结构更清晰、更简洁、更容易让人理解，从而减少读懂代码的时间。
     *
     * 但大量的递归调用会建立函数的副本，会消耗大量的时间和内存，而迭代则不需要此种付出。
     *
     * @param n
     *            初始参数
     * @return 计算结果
     */
    public int fbiRecursive(int n) {
        if (n < 2) {
            return n == 0 ? 0 : 1;
        }
        return fbiRecursive(n - 1) + fbiRecursive(n - 2);
    }

    /**
     * 递归阶乘
     *
     * @param n
     *            初始值
     * @return 返回结果
     */
    public int factorial(int n) {
        return n == 1 ? 1 : n * factorial(n - 1);
    }

    public void charReverseOrder(char[] input, int i) {
        // 递归调用,当遇到 # 结束递归调用且不打印相应数据
        if (input[i] != '#') {
            charReverseOrder(input, i + 1);
        }
        // 这里负责输出
        if (input[i] != '#') {
            System.out.printf(input[i] + "");
        }
    }

    /**
     * 数组二分查找
     *
     * @param data
     *            数组
     * @param target
     *            目标数字
     */
    public void binarySearchArray(int[] data, int target) {
        int length = data.length;
        int start = 0;
        while (true) {
            int temp = (length + start) / 2;
            if (target > data[temp]) {
                start = temp + 1;
                continue;
            }
            if (target < data[temp]) {
                length = temp - 1;
                continue;
            }
            if (target == data[temp]) {
                log.info("{} 位于 data 的下标为:{}", target, temp);
                break;
            }
            log.info("{} 不在 data 中", target, temp);
            break;
        }
    }

    /**
     * 递归二分朝招
     *
     * @param data
     * @param start
     * @param end
     * @param target
     * @return
     */
    public int recursiveFind(int[] data, int start, int end, int target) {
        int mid = (start + end) / 2;
        if (data[mid] > target) {
            return recursiveFind(data, start, mid - 1, target);
        } else if (data[mid] < target) {
            return recursiveFind(data, mid + 1, end, target);
        } else if (data[mid] == target) {
            return mid;
        }
        return -1;
    }

    /**
     * x 柱子 n : 从上往下顺序，1......n
     *
     * 目标，从 x -> z 且过程中 x y z 不能出现最顶层数字大于下层数字的情况，打印转移路径情况
     *
     * @param n
     *            目标数
     * @param x
     *            x 轴
     * @param y
     *            y 轴 借助轴
     * @param z
     *            z 轴
     */
    public void hambota(int n, String x, String y, String z) {
        // 最后一个盘子实现罗盘
        if (n == 1) {
            log.info("{} --> {}", x, z);
        } else {
            // 将 n-1 个盘子从 x 借助 z 到 y 上
            hambota(n - 1, x, z, y);
            // 将 n 个盘子从 x 移动 z,因为 z 已经是最后一个盘子了，因此可以直接移动到 z
            log.info("{} --> {}", x, z);
            // 将 n-1 个盘子从 y 借助 x 到 z 上
            hambota(n - 1, y, x, z);
        }
    }

    @Test
    public void tss(){
        hambota(10, "x", "y", "z");

    }

    @Test
    public void fbiTest1() {
        for (int i = 1; i < 40; i++) {
            fbiIteration(i);
        }
    }

    @Test
    public void fbiTest3() {
        // 斐波那契：
        // 1 2 3 4 5 6 7 8 9 10
        // 1 1 2 3 5 8 13 21 34 55
        for (int i = 1; i < 40; i++) {
            int result = fbiRecursive(i);
            fbiPrint(result);
        }
    }

    @Test
    public void factorial1() {
        log.info("5! 阶乘结果: {}", factorial(10));
    }

    @Test
    public void charReverse() {
        String chars = "I love you !#";
        charReverseOrder(chars.toCharArray(), 0);
    }

    @Test
    public void binaryArray() {
        int[] data = {1, 2};
        binarySearchArray(data, 1);
        int i = recursiveFind(data, 0, data.length - 1, 2);
        log.info(i + "");
    }
}

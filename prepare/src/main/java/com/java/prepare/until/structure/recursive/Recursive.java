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

    public void binarySearchArray(int[] data, int target) {
        int length = data.length;
        int start = 0;
        while (true) {
            int temp = (length + start) / 2;
            if (target > data[temp]) {
                start = temp;
                continue;
            }
            if (target < data[temp]) {
                length = temp;
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
        int[] data = {1, 11, 42, 44, 45, 56, 77, 88, 90};
        binarySearchArray(data, 1);
        binarySearchArray(data, 11);
        binarySearchArray(data, 42);
        binarySearchArray(data, 44);
        binarySearchArray(data, 45);
        binarySearchArray(data, 56);
    }
}

package com.java.prepare.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        //int[] arr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8,9};
        int[] arr = new int[]{5, 4, 3, 2, 1, 0, -1, -2, -3, -4};
        //int[] arr = new int[]{49, 38, 65, 97, 64, 49, 27, 49, 78};
        //int[] arr = new int[]{49, 38, 49, 97, 49, 49, 49, 49, 49, 49, 38, 49, 97, 49, 49, 49, 49, 49};
        //int[] arr = new int[]{49, 38, 65, 97, 65, 13, 27, 49, 78};
        //int[] arr = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        //int[] arr = new int[]{1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1,1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1};
        //int[] arr = new int[]{49, 65, 38, 97, 49, 78, 27, 11, 49, 49, 65, 38, 97, 49, 78, 27, 11, 49};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 快速排序
     *
     * @param arr 要排序的数组
     */
    private static void quickSort(int[] arr) {

        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }


    /**
     * 快排核心算法,递归实现
     *
     * @param arr   要排序的数组
     * @param left  起始索引
     * @param right 结束索引
     */
    private static void quickSort(int[] arr, int left, int right) {
        /*长度大于4则走快速排序,否则走插入排序*/
        if (left < right) {
            /*1、分割 分割完成了将返回基准值的正确索引位置*/
            int baseIndex = partition(arr, left, right);
            /*2、递归对分割后的两个子数组继续执行排序,由于还是上面基准值的为止已经确定了,因此可以排除基准值索引*/
            quickSort(arr, left, baseIndex - 1);
            quickSort(arr, baseIndex + 1, right);
        }
    }

    /**
     * 分割数组  找一个基准值,将数组分成两部分,一部分比基准值小,另一部分比基准值大
     *
     * @param arr   需要分割的数组
     * @param left  左起始索引
     * @param right 右结束索引
     * @return 基准值在整个数组中的正确索引
     */
    private static int partition(int[] arr, int left, int right) {
        //基准值,这里取最左边的值,这是不合理的
        int base = arr[left];
        /*2、开始分割*/
        //记录前后索引初始值,后面会用到
        int i = left;
        int j = right;
        while (true) {
            /*先从左向右找,然后从右向左找,顺序不能乱,如果乱了那么需要改变代码*/
            //先从左往右边找,直到找到大于等于base值的数
            //i一定小于等于j,等于j时说明left所在的数base就是最大数
            while (arr[++i] < base && i < j) {
            }
            //后从右边往左找,直到找到小于等于base值的数
            //j可能小于等于i
            //j也可能等于left,等于left时说明left所在的数base就是最小的数
            while (arr[j] > base) {
                --j;
            }
            //上面的循环结束表示找到了位置(i<j)或者(i>=j)了
            //如果是找到了位置,那么交换两个数在数组中的位置
            if (i < j) {
                swap(arr, i, j);
            } else {
                break;
            }
            //如果还要继续分割,那么j--,该操作可以让与base相同的j继续向中间靠拢而不是停在原地
            --j;
        }
        /*3、寻找基准值的在数组的正确位置,位置应该在j的值*/
        arr[left] = arr[j];
        arr[j] = base;
        /*4、返回基准值的正确索引*/
        return j;
    }

    /**
     * 交换元素
     *
     * @param arr 数组
     * @param a   元素的下标
     * @param b   元素的下标
     */
    private static void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
    }
}


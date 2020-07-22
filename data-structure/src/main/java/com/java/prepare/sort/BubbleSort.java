package com.java.prepare.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author xuweizhi
 * @since 2020/06/08 21:16
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] a = {5, 6, 1, 8, 6, 5, 9, 2, 3};
        bubbleSort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 传统的赋值交换，我们采用申明中间变量的方法进行复制
     *
     * @param a
     * @param i
     * @param j
     */
    private static void swap01(int[] a, int i, int j) {
        int tmpt = a[j];
        a[j] = a[i];
        a[i] = tmpt;
    }


    //a[i] ^= a[j]; 等同于a[i] = a[i]^a[j],少写点  只写8位
    //假定此时是6,1;第一步：a[i]=0000 0110 ，a[j] = 0000 0001;

    /**
     * 计算后
     * 0000 0110
     * 0000 0001  ^
     * -------------
     * a[i]        0000 0111
     * <p>
     * 第二步：
     * 0000 0001
     * 0000 0111   ^
     * ----------------
     * a[j]        0000 0110
     * <p>
     * 第三步：
     * 0000 0111
     * 0000 0110      ^
     * -------------
     * a[i]        0000 0001
     */
    private static void swap(int[] a, int i, int j) {
        a[i] ^= a[j];
        a[j] ^= a[i];
        a[i] ^= a[j];
    }


    private static void bubbleSort(int[] a) {
        int length = a.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (a[j + 1] < a[j])
                    //swap(a,j+1,j);
                    swap01(a, j + 1, j);
            }
        }
    }

    /**
     * 冒泡
     */
    @Test
    public void test() {
        int[] arr = new int[]{9, -8, 45, 3, -1, 7};
        //外层循环控制循环次数
        //第一次循环比较需要比较五次,然后选出最大值 9,排在末尾
        //第二次循环只需要比较四次,选出第二大的值 8,排在倒数第二的位置
        //第n次循环 需要比较 arr.length-n 次
        //最后一次循环 需要比较一次
        for (int i = 0; i < arr.length - 1; i++) {
            //内层循环控制比较的相邻的两个数
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //如果前一个数大于后一个数,则交换位置
                if (arr[j] > arr[j + 1]) {
                    arr[j] ^= arr[j + 1];
                    arr[j + 1] ^= arr[j ];
                    arr[j] ^= arr[j + 1];
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 选择排序
     */
    @Test
    public void test1() {
        //int[] arr = new int[]{9, 8, 5, 3, 1, 7};
        int[] arr = new int[]{49, 38, 65, 97, 76, 13, 27, 49, 78};
        //外层循环控制第一个被比较的数索引   [0 - (arr.length - 1) -1]
        //第一次循环比较需要比较五次,然后选出最小值 9,排在首位
        //第二次循环只需要比较四次,选出第二小的值 8,排在第二位
        //第n次循环 需要比较 arr.length-n 次
        //最后一次循环 需要比较一次
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            //内层循环控制第二个被比较的数索引 [i+1 ~ arr.length-]
            for (int j = i + 1; j < arr.length; j++) {
                //记录该轮最小的数的索引min
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            //内层循环结束之后，判断索引min是否还是和i相等,不相等则说明有比arr[i]还小的数arr[min]，交换元素
            if (min != i) {
                arr[i] = arr[i] ^ arr[min];
                arr[min] = arr[i] ^ arr[min];
                arr[i] = arr[i] ^ arr[min];
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 插入排序
     */
    @Test
    public void test2() {
        int[] arr = new int[]{9, 8, 5, 3, 1, 7};
        int j;
        //外层循环控制右侧未被排序的数 假设"第一个数"是"已经"排好序的,因此 未排序的数据从第二个数开始取
        for (int i = 1; i < arr.length; i++) {
            int norSort = arr[i];
            //内层循环控制左侧已被排序的数,从最大的已排序的数开始比较
            //如果未排序的数小于已排序的数arr[j],则将arr[j]像右移动一位
            for (j = i - 1; j >= 0 && norSort < arr[j]; j--) {
                arr[j + 1] = arr[j];
            }
            //如果未排序的数大于已排序的数arr[j],则将arr[j+1]赋值给norSort,这就是为排序的数需要插入的已排序数据中的位置
            arr[j + 1] = norSort;
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 希尔排序
     */
    @Test
    public void test3() {
        int[] arr = new int[]{49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
        int j;
        //希尔增量初始gap=arr.length / 2   增量每次减半gap /= 2  直到等于0,结束希尔排序
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            /*内部对每次分组的元素采用插入排序,因此,与传统插入排序不同的是,这里的插入排序实现了数据的跳跃式移动*/
            //外层循环控制某分组右侧未被排序的数,同样假设"第一个数"是"已经"排好序的,因此 未排序的数据从某分组第二个数即0+gap的索引处开始取
            for (int i = gap; i < arr.length; i++) {
                int norSort = arr[i];
                //内层循环控制某分组左侧已被排序的数,从最大的已排序的数开始比较,同样假设"第一个数"是"已经"排好序的,即0索引的数
                //如果未排序的数小于已排序的数arr[j],则将arr[j]像右移动j+gap位
                for (j = i - gap; j >= 0 && norSort < arr[j]; j -= gap) {
                    arr[j + gap] = arr[j];
                }
                //如果未排序的数大于已排序的数arr[j],则将arr[j+gap]赋值给norSort,这就是为排序的数需要插入的已排序数据中的位置
                arr[j + gap] = norSort;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

}

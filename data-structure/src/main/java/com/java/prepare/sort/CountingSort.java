package com.java.prepare.sort;

import java.util.Arrays;

public class CountingSort {
    public static void main(String[] args) {
        //要求数组元素为整数
        //int[] arr = new int[]{3, 3, 5, 7, 2, 4, 10, 1, 13, 15, 3, 5, 6};
        int[] arr = new int[]{49, 38, 65, 97, 65, 13, 27, 49, 78};
        //int[] arr = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        //int[] arr = new int[]{5, 4, 3, 2, 1, 0, -1, -2, -3};
        //int[] arr = new int[]{1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1};
        /*1、获取最大值和最小值  这一步应该是预先知道的*/
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        /*2、准备辅助计数数组 大小为max-min+1*/
        int[] help = new int[max - min + 1];
        /*2、分配:辅助计数数组填充*/
        //找出每个元素value出现的次数，找到一次就让辅助数组的value-min索引处的值自增1
        for (int value : arr) {
            help[value - min]++;
        }
        System.out.println("help填充后" + Arrays.toString(help));
        /*3、收集:根据辅助数组从index=0开始填充目标数组
        循环判断辅助数组索引为i的元素值help[i]--是否>0，如果是，则原数组arr[index++]=i + min */
        int index = 0;
        for (int i = 0; i < help.length; i++) {
            while (help[i]-- > 0) {
                arr[index++] = i + min;
            }
        }
        System.out.println("arr排序后" + Arrays.toString(arr));
        System.out.println("help使用后" + Arrays.toString(help));
    }
}


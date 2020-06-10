package com.java.prepare.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = new int[]{49, 38, 65, 97, 76, 13, 27, 49, 78};
        mergeSort(arr, new int[arr.length], 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 并归排序
     *
     * @param arr      要排序的数组
     * @param aidedArr 辅助数组
     * @param l        拆分的左数组的起始索引
     * @param r        拆分的右数组的结束索引
     */
    private static void mergeSort(int[] arr, int[] aidedArr, int l, int r) {
        //如果数组大于等于两个元素，则进行拆分，否则就递归返回
        if (l < r) {
            /*1、拆分*/
            //算出拆分的中值，作为左数组的结束索引
            int mid = (r + l) / 2;
            //拆分为左子数组，然后将左子数组作为父数组继续递归拆分，直到拆分为只有一个元素的两个"有序"子数组
            mergeSort(arr, aidedArr, l, mid);
            //拆分为右子数组，然后继续递归拆分，直到拆分为只有一个元素的两个"有序"子数组
            mergeSort(arr, aidedArr, mid + 1, r);
            /*2、拆分到不能再拆了，即一个子数组只有一个元素，那么就算拆分完毕，开始两两排序合并*/
            merge(arr, aidedArr, l, mid, mid + 1, r);
        }
    }

    /**
     * 排序-合并
     *
     * @param arr        需要排序的数组
     * @param aidedArr   辅助数组
     * @param leftStart  左数组的起始索引
     * @param leftEnd    左数组的结束索引
     * @param rightStart 右数组的起始索引
     * @param rightEnd   右数组的结束索引
     */
    private static void merge(int[] arr, int[] aidedArr, int leftStart, int leftEnd, int rightStart, int rightEnd) {
        //备份获取起始索引m,在后面会用到;获取该两个相邻子数组的元素个数numElements,后面会用到
        int m = leftStart;
        int numElements = rightEnd - leftStart + 1;
        //如果左数组起始位置小于等于左结束位置，并且右数组起始位置小于等于右结束位置，那么比较它们相同的相对位置的元素大小，并且将较小的元素加入到新的数组对应的索引位置（从左起始索引开始）中
        //然后被添加的元素位置相应的自增1,继续循环比较,直到其中一个条件不满足,结束循环
        while (leftStart <= leftEnd && rightStart <= rightEnd) {
            aidedArr[m++] = arr[leftStart] <= arr[rightStart] ? arr[leftStart++] : arr[rightStart++];
        }
        //如果左数组起始位置小于等于左结束位置,说明上面的循环并没有将左数组的元素添加完毕,继续添加
        while (leftStart <= leftEnd) {
            aidedArr[m++] = arr[leftStart++];
        }
        //如果右数组起始位置小于等于右结束位置,说明上面的循环并没有将右数组的元素添加完毕,继续添加
        while (rightStart <= rightEnd) {
            aidedArr[m++] = arr[rightStart++];
        }
        //然后再将新数组的元素拷贝到原数组对应索引处,这一步是需要的,这保证了后续排序合并元素的有序性
        for (int j = 0; j < numElements; j++, rightEnd--) {
            arr[rightEnd] = aidedArr[rightEnd];
        }
    }
}


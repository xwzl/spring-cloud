package com.spring.base.algorithm;

import java.util.Arrays;
import java.util.List;

/**
 * 二分查找法
 *
 * @author xuweizhi
 * @since 2019/09/30 18:37
 */
public class BinarySearch {


    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 5, 7, 15, 25, 30, 36, 39, 51, 67, 78, 80, 82, 85, 91, 92, 97);
        search(list, 0, list.size() - 1, 31);
    }

    private static void search(List<Integer> list, int start, int end, int value) {
        if (start > end) {
            throw new RuntimeException("未找到对应的值");
        }
        int middle = (end + start) / 2;
        if (list.get(middle) > value) {
            search(list, start, middle - 1, value);
        } else if (list.get(middle) < value) {
            search(list, middle + 1, end, value);
        } else {
            System.out.println("找到值" + value);
        }

    }
}

package com.spring.base.replenish;

import java.util.ArrayList;
import java.util.List;

/**
 * Java 数组知识补充，与 go 做对比
 *
 * @author xuweizhi
 * @since 2019/09/29 11:18
 */
public class ArrayReplenish {

    public static void main(String[] args) {
        //first();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
            System.out.println(""+list.size());
        }
    }

    private static void first() {
        // Java 与 go 的差别： Java 中无论数组的长度是多少，数字的基础类型定型，其相同的基础类型一样就可以比较
        // go 中，数组的类型与数组的长度挂钩，长度不一样的相同基础类型，其本质上也不是一个类型，因此不能做比较
        int[] array = {1, 2, 3, 4, 5};
        int[] arrayCompare = {1, 2, 3, 4, 5, 6};
        System.out.println(array == arrayCompare);
    }
}

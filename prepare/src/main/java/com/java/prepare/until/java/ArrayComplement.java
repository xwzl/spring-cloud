package com.java.prepare.until.java;

import org.junit.Test;

/**
 * Java 数组知识补充
 *
 * @author xuweizhi
 * @since 2020/06/07 20:54
 */
public class ArrayComplement {


    @Test
    public void testArray() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < a.length; i++) {
            if (--a[i] % 2 == 0) {
                System.out.printf("%d %d \n", i, a[i]);
            }
        }
    }

}

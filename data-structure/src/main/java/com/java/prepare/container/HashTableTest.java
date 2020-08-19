package com.java.prepare.container;

import org.junit.Before;
import org.junit.Test;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * Hash Table 测试
 *
 * @author xuweizhi
 * @since 2020/08/19 11:15
 */
public class HashTableTest {
    Map<String, Integer> hashTable = new Hashtable<>();

    @Before
    public void init() {
        for (int i = 1; i <= 13; i++) {
            hashTable.put(i + "", i);
        }
    }

    @Test
    public void test1() {
        Set<Map.Entry<String, Integer>> entries = hashTable.entrySet();
    }
}

package com.spring.base.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Stream api 表达式
 *
 * @author xuweizhi
 * @since 2019/11/11 15:35
 */
public class StreamApiTest {

    @Test
    public void flatMap() {
        List<List<String>> lists = new ArrayList<>();
        lists.add(Arrays.asList("Java 入门到放弃", "Python 入门到放弃"));
        lists.add(Arrays.asList("C++ 入门到放弃", "Go 入门到放弃"));
        lists.stream()
                .flatMap(Collection::stream)
                .sorted(String::compareTo)
                .forEach(System.out::println);
    }
}

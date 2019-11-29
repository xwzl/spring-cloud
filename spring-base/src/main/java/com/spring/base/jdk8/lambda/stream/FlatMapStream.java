package com.spring.base.jdk8.lambda.stream;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xuweizhi
 * @since 2019/11/28 9:14
 */
public class FlatMapStream {

    public static void main(String[] args) {

        Supplier<String> stringSupplier = () -> UUID.randomUUID().toString();
        List<String> list1 = Stream.generate(stringSupplier).limit(100000).collect(Collectors.toList());
        List<String> list2 = Stream.generate(stringSupplier).limit(1000).collect(Collectors.toList());
        System.out.println("parallelStream排序开始");
        long startTime = System.nanoTime();

        list1.parallelStream()
                .flatMap(value1 -> list2.stream().map(value2 -> value2 + "#" + value1))
                .map(s -> s.split("#"))
                .flatMap(Arrays::stream)
                .distinct()
                .sorted(String::compareTo)
                .count();
        long endTime = System.nanoTime();

        System.out.println("parallelStream排序耗时:" + TimeUnit.NANOSECONDS.toMillis(endTime - startTime));
        long startTime3 = System.nanoTime();

        System.out.println("Stream排序开始");
        list1.stream()
                .flatMap(value1 -> list2.stream().map(value2 -> value2 + "#" + value1))
                .map(s -> s.split("#"))
                .flatMap(Arrays::stream)
                .distinct()
                .sorted(String::compareTo)
                .count();
        long endTime3 = System.nanoTime();
        System.out.println("Stream排序耗时:" + TimeUnit.NANOSECONDS.toMillis(endTime3 - startTime3));

        System.out.println("loop排序开始");
        long startTime1 = System.nanoTime();
        List<String> toSort = new ArrayList<>();
        Set<String> uniqueValues = new HashSet<>();
        for (String value1 : list1) {
            for (String value2 : list2) {
                String s = value2 + "#" + value1;
                String[] split = s.split("#");
                for (String s1 : split) {
                    if (uniqueValues.add(s1)) {
                        toSort.add(s1);
                    }
                }
            }
        }
        toSort.sort(String::compareTo);
        /*for (String s1 : toSort) {
            System.out.println(s1);
        }*/
        long endTime1 = System.nanoTime();
        System.out.println("loop排序耗时:" + TimeUnit.NANOSECONDS.toMillis(endTime1 - startTime1));
    }
}

package com.spring.base.jdk8.lambda.stream;

import java.util.StringJoiner;

/**
 * @author xuweizhi
 * @date 2018/11/30 14:38
 */
public class CollectorResourceRead {

    /**
     * 源码解读
     * 对于Collectors静态工厂来说，其实现一共分为两种情况
     * 1. 通过CollectorImpl来实现
     * 2. 通过reducing方法来实现；reducing方法本身又是通过CollectorImpl来实现
     */
    public static void main(String[] args) {
        StringJoiner sj = new StringJoiner("￥", "<span>", "</span>");
        sj.add("Hello");
        sj.add("Map");
        System.out.println(sj.toString());
    }
    /**
     * <pre>
     *     {@code
     *     Map<City, Set<String>> namesByCity = people.stream().collect(groupingBy(Person::getCity,
     *     TreeMap::new,mapping(Person::getLastName,toSet())));}
     * </pre>
     */
    // 最终实现还是得看源码 我熟悉但是你不熟悉
    // Collector<T, ?, List<T>> toList() {
    //    return new Collectors.CollectorImpl<>((Supplier<List<T>>) ArrayList::new, List::add,
    //            (left, right) -> { left.addAll(right); return left; },
    //            CH_ID);
    //}
}

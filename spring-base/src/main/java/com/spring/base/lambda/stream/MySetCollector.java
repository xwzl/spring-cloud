package com.spring.base.lambda.stream;


import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Collector<T, A, R>
 * T -> T 流中遍历的数据类型
 * A -> Set<T> 中间结果容器类型
 * R -> Set<T> 返回的结果容器类型
 *
 * @author xuweizhi
 * @date 2018/11/30 10:40
 */
@Slf4j
public class MySetCollector<T> implements Collector<T, Set<T>, Set<T>> {


    /**
     * 提供一个空的容器，供Collector的后续方法调用
     *
     * @return 返回一个初始化容器
     */
    @Override
    public Supplier<Set<T>> supplier() {
        //log.info("自定义Collector 初始化容器");
        return HashSet<T>::new;
    }

    /**
     * 第一个参数表示 中间结果容器
     * 第二个参数表示 流中遍历的一个值
     *
     * @return 累加器
     */
    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        //log.info("中间操作累加器");
        //return (ts, e) -> ts.add(e);
        return Set<T>::add;
    }

    /**
     * 合并多线程分发的容器
     *
     * @return 返回最终结果
     */
    @Override
    public BinaryOperator<Set<T>> combiner() {
        //log.info("并行流合并器");
        return (set1, set2) -> {
            set1.addAll(set2);
            return set1;
        };
    }

    /**
     * 中间容器类型和最终结果类型一致，则不会被调用，否则与之相反
     *
     * @return 返回最终结果
     */
    @Override
    public Function<Set<T>, Set<T>> finisher() {
        //log.info("返回最终的容器");
        return Function.identity();
    }

    /**
     * @return 返回一个结合，标志集合的特性
     */
    @Override
    public Set<Characteristics> characteristics() {
        //log.info("返回结果的特性");
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.UNORDERED));
    }

    public static void main(String[] args) {
        List<String> lists = Arrays.asList("12", "dafa", "fd");
        Set<String> collect = lists.stream().collect(new MySetCollector<>());
        System.out.println(collect);
    }

}

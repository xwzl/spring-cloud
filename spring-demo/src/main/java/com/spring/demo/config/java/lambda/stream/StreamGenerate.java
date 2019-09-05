package com.spring.demo.config.java.lambda.stream;

import java.util.IntSummaryStatistics;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xuweizhi
 * @date 2018/11/27 14:59
 */
public class StreamGenerate {

    /**
     * 流一旦被操作，使用关闭，就不能被重复操作
     */
    public static void main(String[] args) {

        //generateMethod();
        //acquireMin();
        //statisticsMethod();
        Stream<String> stringStream = Stream.of("Hello", "hello", "May", "may", "Marry","hello","Marry");
        stringStream.distinct().collect(Collectors.toList()).forEach(System.out::println);
    }

    private static void statisticsMethod() {
        Stream<Integer> ints = Stream.of(1, 2, 3, 4, 5, 6, 7);
        IntSummaryStatistics statistics = ints.filter(integer -> integer > 2).mapToInt(value -> value * 2).skip(2).limit(2).summaryStatistics();
        System.out.println(statistics.getMax());
        System.out.println(statistics.getMin());
        System.out.println(statistics.getSum());
    }

    private static void acquireMin() {
        Stream<Integer> ints = Stream.of(1, 2, 3, 4, 5, 6, 7);
        ints.filter(integer -> integer > 2).mapToInt(value -> value * 2).skip(2).limit(2).min().ifPresent(System.out::println);
    }

    private static void generateMethod() {
        /*
          无限流
         */
        Stream<String> stream = Stream.generate(UUID.randomUUID()::toString);
        stream.findFirst().ifPresent(System.out::println);

        /*
          无限规律流
         */
        Stream.iterate(1, integer -> {
            return integer + 1;
        }).limit(1).forEach(System.out::println);
        Stream<Integer> ints = Stream.of(1, 2, 3, 4, 5, 6, 7);

        int sum = ints.filter(integer -> integer > 2)
                //避免性能自动拆箱装箱带来的性能损耗
                .mapToInt(value -> value * 2)
                //忽略掉前两个元素
                .skip(2).limit(2).sum();

        System.out.println(sum);
    }


}

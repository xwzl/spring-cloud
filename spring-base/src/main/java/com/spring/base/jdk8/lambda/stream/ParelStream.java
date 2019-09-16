package com.spring.base.jdk8.lambda.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author XuWeiZhi
 * @date 2018-11-28 00:02 星期三 Java8
 **/
public class ParelStream {

    public static void main(String[] args) {
        moreThread();
        //my();
        //List<String> list = Arrays.asList("Hello World", "World Hello", "Hello Stream");
        //list.stream()
        //        //返回的Stream<String[]>
        //        .map(s -> s.split(" "))
        //        //合并流 把数据集合流 转化为数据流
        //        .flatMap(Arrays::stream).distinct()
        //        .forEach(System.out::println);
    }

    public static void my() {
        List<String> lists = Arrays.asList("hello", "Map", "ad");
        //存在短路 对流的操作相当于AOP 流当做一个数据容器，对流的每一个操作，在循环的时候，会在内部添加相应操作
        lists.stream().map(s -> {
            int length = s.length();
            System.out.println(s);
            return length;
        }).filter(length -> length == 5).findFirst().ifPresent(System.out::println);
        for (String list : lists) {
            int length = list.length();
            if (length == 5) {
                System.out.println(length);
                break;
            }
        }
    }

    private static void moreThread() {
        List<String> list = new ArrayList<>(5000000);
        for (int i = 0; i < 5000000; i++) {
            list.add(UUID.randomUUID().toString());
        }

        System.out.println("排序开始");
        long start = System.nanoTime();
        //count 终止操作来执行流 串型流 相当于单线程
        //list.stream().sorted().collect(Collectors.toList());//5708
        //相当于多线程 底层使用fork join 把大任务派生成小任务 小任务执行完 有返回给大任务
        //list.parallelStream().sorted().collect(Collectors.toList());//3032
        long end = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(end - start);
        System.out.println("排序耗时:" + millis);
    }
}

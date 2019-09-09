package com.spring.base.lambda;

import com.spring.base.guava.Student;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * @author xuweizhi
 * @since 2019/08/26 15:57
 */
public class MyStreamTest {

    private List<Student> list;

    @Before
    public void init() {
        Student student1 = new Student("zhangsan", 82, 21);
        Student student2 = new Student("lishi", 92, 21);
        Student student5 = new Student("lisshi", 86, 12);
        Student student6 = new Student("lishi", 93, 21);
        Student student7 = new Student("lisshi", 93, 12);
        Student student3 = new Student("wangwu", 72, 12);
        Student student4 = new Student("zhaoliu", 32, 41);

        list = Arrays.asList(student1, student2, student3, student4, student5, student6, student7);
    }

    @Test
    public void testCollector() {

        Map<String, Student> collect = list.stream().collect(groupingBy(Student::getName, collectingAndThen(minBy(Comparator.comparingInt(Student::getScore)), Optional::get)));

        // java 8 有可能没有这个 api
        //Map<String, Student> collect = list.stream().collect(
        //        toMap(Student::getName, Function.identity(), BinaryOperator.minBy(Comparator.comparingInt(Student::getScore)))
        //);
        System.out.println(collect);

        Set<Map.Entry<String, Student>> entrySet = collect.entrySet();

        for (Map.Entry<String, Student> entry : entrySet) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        Map<String, List<Student>> collect1 = list.stream().collect(groupingBy(Student::getName, toList()));
        for (Map.Entry<String, List<Student>> entry : collect1.entrySet()) {
            System.out.println(entry);
        }

    }

    /**
     * 区间段求值
     */
    @Test
    public void test() {
        Map<Boolean, Map<Boolean, List<Student>>> collect = list.stream().collect(partitioningBy(student -> student.getScore() > 80, partitioningBy(student -> student.getScore() > 90)));
        for (Map.Entry<Boolean, Map<Boolean, List<Student>>> entry : collect.entrySet()) {
            Map<Boolean, List<Student>> value = entry.getValue();
            for (Map.Entry<Boolean, List<Student>> booleanListEntry : value.entrySet()) {
                System.out.println(booleanListEntry.getKey() + ":" + booleanListEntry.getValue());
            }
        }

        //Map<Boolean, List<Student>> listMap = list.stream().collect(partitioningBy(student -> student.getScore() > 85, toList()));
        //for (Map.Entry<Boolean, List<Student>> entry : listMap.entrySet()) {
        //    entry.getValue().forEach(System.out::println);
        //}
    }

    /**
     * Collectors 拼接操作
     */
    @Test
    public void collectJoin() {
        String collect = list.stream().map(Student::getName).collect(joining(",", "begin", "begin"));
        System.out.println(collect);
    }

    /**
     * 分组
     */
    @Test
    public void groupList() {
        //根据分数 名字分组
        final Map<Integer, Map<String, Long>> collect = list.stream().collect(groupingBy(Student::getScore, groupingBy(Student::getName, counting())));
        for (Map.Entry<Integer, Map<String, Long>> entry : collect.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    /**
     * 合并
     */
    @Test
    public void flatMap() {
        List<String> list1 = Arrays.asList("1", "2", "3");
        List<String> list2 = Arrays.asList("A", "B", "C");
        list1.stream().flatMap(s -> list2.stream().map(s1 -> s1 + s)).forEach(System.out::println);
    }

    /**
     * 数组求和
     */
    @Test
    public void summarizingDouble() {

        Student result = new Student();
        Student student = list.stream().min(Comparator.comparingInt(Student::getScore)).orElseGet(() -> null);
        DoubleSummaryStatistics collect = list.stream().collect(Collectors.summarizingDouble(Student::getAge));

        System.out.println(collect.getMin());
        System.out.println(collect.getMax());
        System.out.println(BigDecimal.valueOf(collect.getAverage()).setScale(2, RoundingMode.CEILING));
        System.out.println(collect.getCount());
        System.out.println(collect);
    }
}

package com.spring.base.lambda;

import com.spring.base.lambda.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

/**
 * @author xuweizhi
 * @since 2019/08/26 15:14
 */
@Slf4j
public class StudentTest {

    private static List<Student> students = new ArrayList<>();

    @Before
    public void init() {
        Student s1 = new Student(1L, "肖战", 15, "浙江");
        Student s2 = new Student(2L, "王一博", 15, "湖北");
        Student s3 = new Student(3L, "杨紫", 17, "北京");
        Student s4 = new Student(4L, "李现", 17, "浙江");

        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
    }

    @Test
    public void testFilter() {
        List<Student> streamStudents = testFilter(students);
        streamStudents.forEach(System.out::println);
    }

    @Test
    public void testMap() {
        //在地址前面加上部分信息，只获取地址输出
        List<String> addresses = students.stream().map(s -> "住址:" + s.getAddress()).collect(Collectors.toList());
        addresses.forEach(System.out::println);
    }

    @Test
    public void testDistinct() {
        //简单字符串的去重
        List<String> list = Arrays.asList("111", "222", "333", "111", "222");
        list.stream().distinct().forEach(System.out::println);
    }

    @Test
    public void testDistinct1() {
        students.stream().distinct().forEach(student -> log.info(student.toString()));
    }

    /**
     * 集合排序（默认排序）
     */
    @Test
    public void testSort() {
        List<String> list = Arrays.asList("333", "111", "222");
        list.stream().sorted().forEach(System.out::println);

        students.stream()
                .sorted((stu1, stu2) -> Long.compare(stu2.getId(), stu1.getId()))
                .sorted((stu1, stu2) -> Integer.compare(stu2.getAge(), stu1.getAge()))
                .forEach(System.out::println);
    }

    /**
     * 集合limit，返回前几个元素
     */
    @Test
    public void testLimit() {
        List<String> list = Arrays.asList("333", "222", "111");
        list.stream().limit(2).forEach(System.out::println);
    }

    /**
     * 集合skip，删除前n个元素
     */
    @Test
    public void testSkip() {
        List<String> list = Arrays.asList("333", "222", "111");
        list.stream().skip(2).forEach(System.out::println);
    }

    /**
     * 求集合中元素的最小值
     */
    @Test
    public void testMin() {
        Student student = students.stream().min(Comparator.comparingInt(Student::getAge)).get();
        System.out.println(student.toString());
    }

    @Test
    public void testMatch() {
        boolean anyMatch = students.stream().anyMatch(s -> "湖北".equals(s.getAddress()));
        if (anyMatch) {
            System.out.println("有湖北人");
        }
        boolean allMatch = students.stream().allMatch(s -> s.getAge() >= 15);
        if (allMatch) {
            System.out.println("所有学生都满15周岁");
        }
        boolean noneMatch = students.stream().noneMatch(s -> "杨洋".equals(s.getName()));
        if (noneMatch) {
            System.out.println("没有叫杨洋的同学");
        }
    }

    /**
     * 集合reduce,将集合中每个元素聚合成一条数据
     */
    @Test
    public void testReduce() {
        List<String> list = Arrays.asList("欢", "迎", "你");
        String appendStr = list.stream().reduce("北京", (a, b) -> a + b);
        System.out.println(appendStr);
    }

    /**
     * 集合的筛选
     */
    private static List<Student> testFilter(List<Student> students) {
        //筛选年龄大于15岁的学生
        //return students.stream().filter(s -> s.getAge() > 15).collect(Collectors.toList());
        //筛选住在浙江省的学生
        return students.stream().filter(s -> "浙江".equals(s.getAddress())).collect(Collectors.toList());
    }

    /**
     * Collectors 拼接操作
     */
    @Test
    public void collectJoin() {
        String collect = students.stream().map(Student::getName).collect(joining(",", "begin", "suffix"));
        System.out.println(collect);
    }

}



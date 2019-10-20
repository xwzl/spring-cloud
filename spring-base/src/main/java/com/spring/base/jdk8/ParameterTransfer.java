package com.spring.base.jdk8;

import com.spring.base.guava.Student;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Java 中参数传递的是值传递还是引用传递呢？
 * <p>
 * Java 中传递的值，不会传递引用即指针
 *
 * @author xuweizhi
 * @since 2019/09/30 22:34
 */
public class ParameterTransfer {

    @Contract(pure = true)
    private static int testBaseType(int x) {
        x = 1;
        return x;
    }

    private static void testReference(@NotNull Student student) {
        student.setAge(1);
        student = new Student();
        student.setAge(11);
    }

    public static void main(String[] args) {
        System.out.println(testBaseType(2));
        Student student = new Student();
        testReference(student);
        System.out.println(student);
    }
}

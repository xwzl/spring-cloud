package com.java.prepare.until.java;

import lombok.Data;

/**
 * 浅克隆，不可隆引用对象
 *
 * @author xuweizhi
 * @since 2020/06/11 23:23
 */
@Data
public class Teacher implements Cloneable {
    private String name;
    private int age;
    private Student stu;

    public static void main(String[] args) throws CloneNotSupportedException {
        Student stu = new Student("李四", 24);
        Teacher tea = new Teacher("张三", 30, stu);
        //使用未做改变的clone方法
        Teacher teaClone = (Teacher) tea.clone();

        /*clone之后改变原对象的数据*/
        //改变stu的数据
        stu.name = "李四改";
        //改变tea的数据
        tea.name = "张三改";

        //结果被克隆的数据的内部类的stu数据也受到了影响,说明未重写的clone方法,实现的只是浅克隆,tea的对象类型属性stu还是指同一个对象
        System.out.println(teaClone);
        System.out.println(tea);
    }


    /**
     * 实现克隆，引用也被修改了
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        //改写clone方法
        Teacher tea = (Teacher) super.clone();
        //获取属性对象,再clone一次,让后设置到被克隆的对象中,返回
        tea.stu = ((Student) tea.stu.clone());
        return tea;
    }

    public Teacher(String name, int age, Student stu) {
        super();
        this.name = name;
        this.age = age;
        this.stu = stu;
    }

    public static class Student implements Cloneable {
        private String name;
        private int age;

        public Student(String name, int age) {
            super();
            this.name = name;
            this.age = age;
        }


        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        /**
         * 实现克隆
         */
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", stu=" + stu +
                '}';
    }
}

package com.java.prepare.until.java;

import java.io.*;

/**
 * 序列化流实现克隆
 */
public class TeacherSerializable implements Serializable {
    private String name;
    private int age;
    private Student stu;


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student stu = new Student("李四", 24);
        TeacherSerializable tea = new TeacherSerializable("张三", 30, stu);
        //内存数组输出流
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        //序列化流
        ObjectOutputStream oos = new ObjectOutputStream(bao);
        //将数据tea写入序列化流中,随后会被传递到内存数组输出流中,将对象序列化为byte[]类型的数据
        oos.writeObject(tea);
        //从内存数组输出流中获取到tea的byte[]类型的数据,传入内存数组输入流
        ByteArrayInputStream bai = new ByteArrayInputStream(bao.toByteArray());
        //将内存数组输入流传给反序列化流,这样也实现了byte[]类型的数据的传递
        ObjectInputStream ois = new ObjectInputStream(bai);
        //使用readObject,从反序列化流中读取数据,将byte[]类型的数据反序列化成Teacher对象
        TeacherSerializable teaClone = (TeacherSerializable) ois.readObject();
        //改变stu的数据
        stu.name = "李四改";
        //改变tea的数据
        tea.name = "张三该";
        //结果被克隆的数据的内部类的stu数据没有受到了影响,说明重写后的clone方法,实现了深克隆
        System.out.println(teaClone);
        System.out.println(tea);
    }


    public TeacherSerializable(String name, int age, Student stu) {
        super();
        this.name = name;
        this.age = age;
        this.stu = stu;
    }

    public static class Student implements Serializable {
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

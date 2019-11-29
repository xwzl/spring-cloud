package com.spring.base.guava;

/**
 * @author xuweizhi
 * @since 2019/11/26 16:16
 */
public class Student {

    private String name;

    private int score;

    private int age;

    public Student() {

    }

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public Student(String name, int score, int age) {
        this.name = name;
        this.score = score;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static int compareStudentByScore(Student student1, Student student2) {
        return student1.getScore() - student2.getScore();
    }

    public static int compareStudentByName(Student student1, Student student2) {
        return student1.getName().compareTo(student2.getName());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", age=" + age +
                '}';
    }
}

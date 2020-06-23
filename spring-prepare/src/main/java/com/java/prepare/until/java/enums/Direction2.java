package com.java.prepare.until.java.enums;

public class Direction2 {
    public static final Direction2 FRONT = new Direction2();
    public static final Direction2 BEHIND = new Direction2("后");
    public static final Direction2 LEFT = new Direction2("左");
    public static final Direction2 RIGHT = new Direction2("右");

    private Direction2() {
    }

    /**
     * 加入一个成员变量
     */
    private String name;

    /**
     * 加入有参构造器
     *
     * @param name
     */
    private Direction2(String name) {
        this.name = name;
    }

    /**
     * 加入一个方法
     *
     * @return
     */
    public String getName() {
        return name;
    }
}

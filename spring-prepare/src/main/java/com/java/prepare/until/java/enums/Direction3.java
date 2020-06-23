package com.java.prepare.until.java.enums;

public abstract class Direction3 {
    public static final Direction3 FRONT = new Direction3("前") {
        @Override
        public void show() {
            System.out.println("我是前面");
        }
    };
    public static final Direction3 BEHIND = new Direction3("后") {
        @Override
        public void show() {
            System.out.println("我是后面");
        }
    };

    public static final Direction3 LEFT = new Direction3("左") {
        @Override
        public void show() {
            System.out.println("我是左面");
        }
    };

    public static final Direction3 RIGHT = new Direction3("右") {
        @Override
        public void show() {
            System.out.println("我是右面");
        }
    };

    /**
     * 加入一个抽象方法.在定义枚举时,必须实现
     */
    public abstract void show();

    private String name;

    private Direction3(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

package com.java.prepare.until.java.enums;

public enum DirectionEnum3 {
    /**
     * 同第三种自定义枚举,构造器默认私有,成员默认public static final修饰
     * 抽象方法在枚举实例中必须实现
     */
    FRONT("前") {
        @Override
        public void show() {
            System.out.println("前面");
        }
    }, BEHIND("后") {
        @Override
        public void show() {
            System.out.println("后面");
        }
    }, LEFT("左") {
        @Override
        public void show() {
            System.out.println("左面");
        }
    }, RIGHT("右") {
        @Override
        public void show() {
            System.out.println("右面");
        }
    };


    private String name;

    /**
     * 这里构造函数实际上默认是私有的
     *
     * @param name
     */
    DirectionEnum3(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    /**
     * 可以有抽象方法
     */
    public abstract void show();
}

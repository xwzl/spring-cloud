package com.java.prepare.until.java.enums;

public enum DirectionEnum2 {
    /**
     * 同第二种自定义枚举,构造器默认私有,成员默认public static final修饰
     */
    FRONT("前"), BEHIND("后"), LEFT("左"), RIGHT("右");

    /**
     * 可以有参数
     */
    private String name;

    /**
     * 这里构造函数实际上默认是私有的
     *
     * @param name
     */
    DirectionEnum2(String name) {
        this.name = name;
    }

    /**
     * 可以有方法
     *
     * @return
     */
    public String getName() {
        return name;
    }
}

package com.spring.base.thread;

/**
 * 不变模式
 *
 * @author xuweizhi
 * @since 2019/09/16 15:24
 */
public final class InvariantMode {

    /**
     * 私有无法被其他对象获取和修改
     */
    private final String name;

    private final String no;

    private final double price;

    /**
     * 创建构造函数时初始化所有对象
     */
    public InvariantMode(String name, String no, double price) {
        this.name = name;
        this.no = no;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getNo() {
        return no;
    }

    public double getPrice() {
        return price;
    }
}

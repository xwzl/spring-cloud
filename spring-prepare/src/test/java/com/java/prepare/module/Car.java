package com.java.prepare.module;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Factory 测试
 * <p>
 * 如果使用传统方式配置下面 Car 的 <bean> 时，Car的每个属性分别对应一个 <property> 元素标签。
 *
 * @author xuweizhi
 * @since 2020/06/15 11:15
 */
@Data
@Slf4j
public class Car {

    private String brand;

    private Double price;

    private String description;

    public Car() {
    }

    public Car(String brand, Double price, String description) {
        this.brand = brand;
        this.price = price;
        this.description = description;
    }

    public Car(String brand) {

    }

    public void init() {
        log.info("初始化方法");
    }

    public void cleanUp() {
        log.info("销毁方法");
    }

}

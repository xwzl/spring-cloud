package com.java.prepare.module;

import lombok.Data;

/**
 * Factory 测试
 * <p>
 * 如果使用传统方式配置下面 Car 的 <bean> 时，Car的每个属性分别对应一个 <property> 元素标签。
 *
 * @author xuweizhi
 * @since 2020/06/15 11:15
 */
@Data
public class Car {

    private String brand;

    private Double price;

    private String description;

}

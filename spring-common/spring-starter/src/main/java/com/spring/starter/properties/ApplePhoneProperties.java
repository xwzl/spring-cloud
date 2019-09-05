package com.spring.starter.properties;

/**
 * 测试属性注入
 *
 * @author xuweizhi
 * @since 2019-08-23
 */
public class ApplePhoneProperties {

    /**
     * 品牌
     */
    private String brand = "默认配置";

    /**
     * 价格
     */
    private Float price = 1f;

    /**
     * 笔记
     */
    private String notes = "默认配置";

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

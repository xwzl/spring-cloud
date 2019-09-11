package com.spring.demo.demo.read;

import lombok.Data;

import java.util.Date;

/**
 * 基础数据类.这里的排序和excel里面的排序一致
 *
 * @author xuweizhi
 **/
@Data
public class DemoData {

    private String string;

    private Date date;

    private Double doubleData;

}
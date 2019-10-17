package com.spring.base.jdk8.json;

import com.spring.base.guava.Student;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author xuweizhi
 * @since 2019/10/17 12:21
 */
@Data
public class JsonModel {

    private Object[] ints;

    private List<Object> list;

    private Map<String, Object> map;

    private int anInt;

    private float aFloat;

    private boolean aBoolean;

    private Integer integer;

    private String value;

    private Date date;

    private Student student;

    private Boolean flag;
}

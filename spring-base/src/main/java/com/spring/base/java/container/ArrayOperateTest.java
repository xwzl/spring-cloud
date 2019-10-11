package com.spring.base.java.container;

import com.alibaba.fastjson.JSONObject;
import com.spring.base.guava.Student;
import org.junit.Test;

import java.util.HashMap;

/**
 * 数组定义回顾
 *
 * @author xuweizhi
 * @since 2019/10/11 21:49
 */
public class ArrayOperateTest {

    /**
     * Java 中的数组不可变序列，但是其中的值为可读字段
     */
    @Test
    public void array() {
        Object[] base = new Object[]{1, 2, new Student()};
        System.out.println(base);
        String json = JSONObject.toJSONString(base);
        System.out.println(json);
        System.out.println("数组转换为 json 后，数据格式与 python 列表对应，Java 中 list 底层本质为数组");
    }

    /**
     * map  Json 字符串测试
     */
    @Test
    public void map() {
        System.out.println(JSONObject.toJSONString(new HashMap<String, Object>(4) {
            {
                put("name", "张三");
                put("age", 2);
            }
        }));
        HashMap<String, Object> params = new HashMap<String, Object>(4) {
            {
                put("name", "张三");
                put("age", 2);
            }
        };
        System.out.println(params);
    }

    /**
     * 非静态代码赋值
     */
    @Test
    public void test() {
        Student student = new Student() {{
            setAge(1);
            setName("张三");
            setScore(20);
        }};
        System.out.println(student);
    }
}

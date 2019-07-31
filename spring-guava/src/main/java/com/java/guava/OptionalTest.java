package com.java.guava;

import org.junit.Test;

import java.util.Optional;

/**
 * @author xuweizhi
 * @since 2019-07-31
 */
public class OptionalTest {

    /**
     * Java Optional 判断第一个数据是否为空，如果为空就用第二个数据
     */
    @Test
    public void optionalTest1() {
        Optional<Integer> optional = Optional.ofNullable(null);
        Integer other = optional.orElse(2);
        System.out.println(other);
    }


    @Test
    public void optionalTest2() {
        User user = new User();
        user.setUsername("women");

        //Optional<User> user2 = Optional.ofNullable(null);
        //System.out.println(user2.get());
    }
}

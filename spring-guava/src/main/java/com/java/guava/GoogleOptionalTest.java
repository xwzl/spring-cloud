package com.java.guava;

import com.google.common.base.Optional;
import org.junit.Test;


/**
 * @author xuweizhi
 * @since 2019-07-31
 */
public class GoogleOptionalTest {

    @Test
    public void googleOptionalTest1() {
        User user = new User();
        user.setUsername("women");
        Optional<User> optionalUser = Optional.of(user);
        boolean equals = optionalUser.equals(optionalUser);
    }

    @Test
    public void googleOptionalNullTest() {
        Optional<User> optional = Optional.fromNullable(null);
        System.out.println(optional);
    }

}

package com.java.guava;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import org.junit.Test;

import java.util.Objects;


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

    /**
     * checkArgument(boolean)	                            检查boolean是否为true，用来检查传递给方法的参数。	                    IllegalArgumentException
     * checkNotNull(T)	                                    检查value是否为null，该方法直接返回value，因此可以内嵌使用checkNotNull。	NullPointerException
     * checkState(boolean)	                                用来检查对象的某些状态。	                                            IllegalStateException
     * checkElementIndex(int index, int size)	            检查index作为索引值对某个列表、字符串或数组是否有效。                     index>=0 && index<size *	IndexOutOfBoundsException
     * checkPositionIndex(int index, int size)	            检查index作为位置值对某个列表、字符串或数组是否有效。                     index>=0 && index<=size *	IndexOutOfBoundsException
     * checkPositionIndexes(int start, int end, int size)	检查[start, end]表示的位置范围对某个列表、字符串或数组是否有效*	        IndexOutOfBoundsException
     */
    @Test
    public void preconditionsTest() {
        // 当为 false 抛出消息
        Preconditions.checkArgument(false, "这是一个错误消息！");
    }

    @Test
    public void equalObjects() {
        boolean b = Objects.equals("a", "a");
        int c = com.google.common.base.Objects.hashCode("c", "a");
        String string = MoreObjects.toStringHelper("fix").add("x", 1).toString();
        System.out.println(string);
        System.out.println(c);
        System.out.println(b);

    }

}

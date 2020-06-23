package com.java.prepare.until.java.enums;

import org.junit.Test;

/**
 * 枚举测试
 */
public class DirectionTest {
    /**
     * 测试自定义枚举1
     */
    @Test
    public void test1() {
        Direction s = Direction.BEHIND;
    }

    /**
     * 测试自定义枚举2
     */
    @Test
    public void test2() {
        //测试2
        Direction2 behind = Direction2.BEHIND;
        //后
        System.out.println(behind.getName());
    }

    /**
     * 测试自定义枚举3
     */
    @Test
    public void test3() {
        //测试3
        Direction3 d2 = Direction3.FRONT;
        System.out.println(d2.getName());
        d2.show();
    }

    /**
     * 测试enum枚举1
     */
    @Test
    public void test4() {
        //测试enum1
        DirectionEnum1 front = DirectionEnum1.FRONT;
    }

    /**
     * 测试enum枚举2
     */
    @Test
    public void test5() {
        //测试enum2
        DirectionEnum2 front = DirectionEnum2.FRONT;
        System.out.println(front.getName());
    }

    /**
     * 测试enum枚举3
     */
    @Test
    public void test6() {
        //测试enum3
        DirectionEnum3 front = DirectionEnum3.FRONT;
        System.out.println(front.getName());
        front.show();
    }

    @Test
    public void test7() {
        DirectionEnum1 de = DirectionEnum1.BEHIND;
        // de = DirectionEnum1.FRONT;
        switch (de) {
            case FRONT:
                System.out.println("你选择了前");
                break;
            case BEHIND:
                System.out.println("你选择了后");
                break;
            case LEFT:
                System.out.println("你选择了左");
                break;
            case RIGHT:
                System.out.println("你选择了右");
                break;
            default:
        }
    }

}


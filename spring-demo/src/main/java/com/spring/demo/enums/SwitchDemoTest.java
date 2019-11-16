package com.spring.demo.enums;

import org.junit.Test;

/**
 * @author xuweizhi
 * @since 2019-08-08
 */
public class SwitchDemoTest {

    @Test
    public void testEnum() {

        SeasonEnum byCode = SeasonEnum.getByMessage("春天");

        switch (byCode) {
            case SPRING:
                System.out.println("spring");
                break;
            default:
                System.out.println("This is a ");
        }

    }
}

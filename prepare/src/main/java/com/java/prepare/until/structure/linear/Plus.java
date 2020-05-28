package com.java.prepare.until.structure.linear;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xuweizhi
 * @since 2020/05/24 21:29
 */
@Slf4j
public class Plus {

    public int i;

    public Plus() {
        i = 1;
        log.info("父类无参构造器:{}", i);
    }

    public Plus(int i) {
        log.info("父类有参构造器:{}", i);
    }
}

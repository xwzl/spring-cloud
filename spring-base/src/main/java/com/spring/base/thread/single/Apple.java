package com.spring.base.thread.single;

import lombok.extern.slf4j.Slf4j;

/**
 * Apple single mode
 *
 * @author xuweizhi
 * @since 2019/09/16 13:46
 */
@Slf4j
public class Apple {

    private static int status = 1;

    private Apple() {
        System.out.println("This object is created !");
    }

    private static int result = 1;

    static {
        log.info(status + ":" + result);
    }


}



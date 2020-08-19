package com.java.prepare.container;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * HashMap
 *
 * @author xuweizhi
 * @since 2020/08/19 15:17
 */
@Slf4j
public class HashMapTest {

    @Test
    public void mapTest() {
        log.info("2 的 31 次方 {}", Long.valueOf(BigDecimal.valueOf(Math.pow(2, 31)).toString()));
        log.info("HashMap max size is {}", 1 << 30);
        log.info("Integer max size is {}", Integer.MAX_VALUE);
        Map<String, String> params = new HashMap<>();

    }

    @Test
    public void phoneDesensitization() {
        String regex = "(\\w{" + 3 + "})(\\w+)(\\w{" + 4 + "})";
        log.info("15280975199".replaceAll(regex, "$1****$2"));
    }
}

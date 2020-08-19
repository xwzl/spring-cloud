package com.java.prepare.container;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 属性类
 *
 * @author xuweizhi
 * @since 2020/08/19 14:48
 */
@Slf4j
public class PropertiesTest {

    @Test
    public void propertiesTest() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File("src/main/resources/redis/redis.properties")));
        log.info(properties.get("redis") + "");
    }
}

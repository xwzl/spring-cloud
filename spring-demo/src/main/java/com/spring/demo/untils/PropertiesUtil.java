package com.spring.demo.untils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 静态属性注入
 *
 * @author xuweizhi
 * @since 2019/09/23 11:35
 */
@Slf4j
public class PropertiesUtil {

    public final static String PROP;

    static {
        InputStream resourceAsStream = PropertiesUtil.class.getResourceAsStream("/config.properties");
        Properties prop = new Properties();
        try {
            prop.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PROP = prop.getProperty("PROP");
        log.info(PROP);
    }

    //public static void main(String[] args){
    //    URL resource = PropertiesUtil.class.getResource("/");
    //    InputStream resourceAsStream = PropertiesUtil.class.getResourceAsStream("/config.properties");
    //    System.out.println(resource);
    //}

}

package com.spring.base.java;

import cn.hutool.core.date.DateUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 加载文件测试
 *
 * @author xuweizhi
 * @since 2020/01/14 10:36
 */
@Data
@Slf4j
public class Book {

    /**
     * 作者
     */
    private String author;

    /**
     * 书名
     */
    private String bookName;

    /**
     * 发型日期
     */
    private Date releaseDate;

    /**
     * 价格
     */
    private Double price;

    /**
     * 版本号
     */
    private int version;

    public void printInfo(Date date) {
        log.info(DateUtil.date(date).toString("HH:mm:ss") + "打印");
    }
}

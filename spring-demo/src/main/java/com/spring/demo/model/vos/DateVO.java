package com.spring.demo.model.vos;

import   io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 测试序列化时间类型
 *
 * @author xuweizhi
 * @since 2019/09/03 11:04
 */
@Data
@ToString
public class DateVO {

    private String value;

    /**
     * java8 yyyy-MM-dd HH:mm:ss
     */
    @Schema(description = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;

    /**
     * java8 yyyy-MM-dd
     */
    @Schema(description = "yyyy-MM-dd")
    private LocalDate localDate;

    /**
     * java8 HH:mm:ss
     */
    //@Schema("HH:mm:ss")
    //private LocalTime localTime;

    @Schema(description = "常用类型")
    private Date date;
}

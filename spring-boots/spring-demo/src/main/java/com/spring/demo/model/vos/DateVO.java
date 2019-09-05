package com.spring.demo.model.vos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    @ApiModelProperty("yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "不能为 null")
    private LocalDateTime localDateTime;

    /**
     * java8 yyyy-MM-dd
     */
    @ApiModelProperty("yyyy-MM-dd")
    @NotNull(message = "不能为 null")
    private LocalDate localDate;

    /**
     * java8 HH:mm:ss
     */
    @ApiModelProperty("HH:mm:ss")
    @NotNull(message = "不能为 null")
    private LocalTime localTime;

    @ApiModelProperty("常用类型")
    @NotNull(message = "不能为 null")
    private Date date;
}

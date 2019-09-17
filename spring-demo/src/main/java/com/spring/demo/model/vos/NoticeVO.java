package com.spring.demo.model.vos;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author 17847
 */
@Data
@ToString
public class NoticeVO {

    private Integer status;

    private Object msg;

    private List<DataVO> data;

}

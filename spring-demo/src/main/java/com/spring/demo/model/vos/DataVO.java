package com.spring.demo.model.vos;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author 17847
 */
@Data
@Builder
@ToString
public class DataVO {

    private Integer noticeId;

    private String noticeTitle;

    private Object noticeImg;

    private Long noticeCreateTime;

    private Long noticeUpdateTime;

    private String noticeContent;
}
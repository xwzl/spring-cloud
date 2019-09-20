package com.spring.demo.model.vos;

import lombok.*;

import java.util.List;

/**
 * @author 17847
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DataVO {

    private Integer noticeId;

    private String noticeTitle;

    private Object noticeImg;

    private Long noticeCreateTime;

    private Long noticeUpdateTime;

    private String noticeContent;

    private List<DataVO> data;
}
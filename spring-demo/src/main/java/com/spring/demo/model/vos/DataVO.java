package com.spring.demo.model.vos;

import lombok.*;

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
}
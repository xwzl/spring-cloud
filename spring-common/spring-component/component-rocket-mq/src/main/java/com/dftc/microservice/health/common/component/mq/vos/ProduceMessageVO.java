package com.dftc.microservice.health.common.component.mq.vos;


import com.dftc.microservice.health.common.component.mq.enums.MessageEnum;
import com.dftc.microservice.health.common.component.mq.enums.ProducerGroupEnum;
import com.dftc.microservice.health.common.component.mq.enums.TageEnum;
import com.dftc.microservice.health.common.component.mq.enums.TopIcEnum;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：huanghongfa
 * @date ：Created in 2019/7/24 0024 16:20
 * @description：生产消息vo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProduceMessageVO {
    /**
     * 消息主题类型
     */
    private TopIcEnum topIcEnum;
    /**
     * 消息标签
     */
    private TageEnum tageEnum;
    /**
     * 生产分组类型
     */
    private ProducerGroupEnum producerGroupEnum;
    /**
     * 生产消息类型
     */
    private MessageEnum messageEnum;
    /**
     * 消息内容
     */
    @NotEmpty(message = "消息内容不能为空")
    private String jsonStr;
    /**
     * 0 同步发送 1 异步发送
     */
    @NotNull
    @Min(value = 0, message = "消息发送方式错误")
    @Max(value = 1, message = "消息发送方式错误")
    private Integer sendingMode;
}

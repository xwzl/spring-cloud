package com.dftc.microservice.health.common.component.mq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mxdddy
 */
@Configuration
@ConditionalOnClass(name = "org.apache.rocketmq.spring.core.RocketMQTemplate")
public class MqAutoConfig {

    @Bean
    @ConditionalOnMissingBean({MQSender.class})
    public MQSender mqSender(RocketMQTemplate rocketMQTemplate) {

        return new MQSender(rocketMQTemplate);
    }
}

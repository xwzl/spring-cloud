package com.dftc.microservice.health.common.component.mq.functionalinterface;

/**
 * @author ：huanghongfa
 * @date ：Created in 2019/7/27 0027 14:33
 * @description：事务消息发送失败业务回滚
 */
public interface ProduceRollbackInterface {

    void rollbackBusiness();
}

package com.dftc.microservice.health.common.component.mq.listener;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;

/**
 * @author ：huanghongfa
 * @date ：Created in 2019/7/27 0027 13:44
 * @description：事务消息监听本地事务
 */
@RocketMQTransactionListener(txProducerGroup = "group_1")
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {

    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<String, Integer>();

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        String transId = (String)msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
        // 一共有三种事务消息状态：
        // 执行本地事务成功后返回commit，这时broker会提交消息给消费端订阅；
        // 若执行失败变为rollback，则自动回滚掉，broker也会删除掉前面发送的prepared消息；
        // 如果本地事务执行超时或返回了Unknow状态，则broker会进行事务回查。
        // todo 执行特定的本地事务
        int status = 0;
        localTrans.put(transId, status);
        if (status == 0) {
            return RocketMQLocalTransactionState.COMMIT;
        }
        if (status == 1) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    /**
     * 事务回查
     *
     * @param msg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        String transId = (String)msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
        RocketMQLocalTransactionState retState = RocketMQLocalTransactionState.COMMIT;
        Integer status = localTrans.get(transId);
        if (null != status) {
            switch (status) {
                case 0:
                    retState = RocketMQLocalTransactionState.UNKNOWN;
                    break;
                case 1:
                    retState = RocketMQLocalTransactionState.COMMIT;
                    break;
                case 2:
                    retState = RocketMQLocalTransactionState.ROLLBACK;
                    break;
            }
        }
        return retState;
    }

}

package com.dftc.microservice.health.common.component.mq;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import com.spring.component.api.version.VersionContent;
import com.spring.component.api.version.VersionUtil;
import com.spring.component.json.JSON;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeanUtils;


import lombok.extern.slf4j.Slf4j;

/**
 * 注：同一个消费组[order-2]必须保持一致的订阅关系 如： [order-2] 订阅了主题[PARTNER-APP]下面的标签[tag0] 那么就不能存在另一个[order-2]
 * 订阅主题[PARTNER-APP]下面的标签[tag1]或者标签[tag2] 如果想另一个消费者订阅 主题[PARTNER-APP]下面的标签[tag1]或者标签[tag2] 那么请重写一个消费组 [order-xxx] ...
 *
 * @author xingxin
 * @createTime 2019/9/3
 */
@Slf4j
// @Component
// @RocketMQMessageListener(consumerGroup = "order-2", topic = "PARTNER-APP", selectorExpression = "tag0")
public abstract class MqListenerDecorator<T> implements RocketMQListener<MessageExt> {

    private final Class<T> messageType;
    private final Method method;

    public MqListenerDecorator() {
        messageType = getMessageType();
        Objects.requireNonNull(messageType);
        method = BeanUtils.findDeclaredMethod(this.getClass(), "dealMessage", messageType);
        Objects.requireNonNull(method);
    }

    @Override
    public void onMessage(MessageExt messageExt) {
        VersionUtil.setVersionContext(messageExt.getProperty(VersionContent.VERSION_HEADER_KEY));
        try {
            method.invoke(this, doConvertMessage(messageExt));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("invoke dealing message error", e);
        }
    }

    private Object doConvertMessage(MessageExt messageExt) {
        if (Objects.equals(messageType, MessageExt.class)) {
            return messageExt;
        } else {
            String str = new String(messageExt.getBody(), StandardCharsets.UTF_8);
            if (Objects.equals(messageType, String.class)) {
                return str;
            } else {
                try {
                    return JSON.fromJson(str, messageType);
                } catch (Exception e) {
                    log.info("convert failed. str:{}, msgType:{}", str, messageType);
                    throw new RuntimeException("cannot convert message to " + messageType, e);
                }
            }
        }
    }

    private Class<T> getMessageType() {
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(this);
        Class<?> superclass;
        while ((superclass = targetClass.getSuperclass()) != null && !superclass.equals(MqListenerDecorator.class)) {
            targetClass = superclass;
        }
        if (superclass == null) {
            throw new RuntimeException("Class is not Valid BaseConsumer:" + this.getClass().getName());
        }
        Type type = targetClass.getGenericSuperclass();
        assert type instanceof ParameterizedType;
        ParameterizedType parameterizedType = (ParameterizedType)type;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (Objects.nonNull(actualTypeArguments) && actualTypeArguments.length > 0) {
            return (Class<T>)actualTypeArguments[0];
        } else {
            throw new RuntimeException("Generic Type not found for Class:" + this.getClass().getName());
        }
    }

    public abstract void dealMessage(T t);
}

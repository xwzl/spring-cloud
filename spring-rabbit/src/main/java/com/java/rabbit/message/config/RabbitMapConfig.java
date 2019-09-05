package com.java.rabbit.message.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuweizhi
 * @date 2019/05/23 10:32
 */
@Configuration
public class RabbitMapConfig {


    /**
     * 示例队列 1
     */
    @Bean
    Queue queue1() {
        return new Queue("queue1");
    }

    /**
     * 示例队列 2
     */
    @Bean
    Queue queue2() {
        return new Queue("queue2");
    }


    /**
     * 示例 TopicExchange
     */
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }


    /**
     * topicChange 与 queue1 路由规则 topic.demo
     */
    @Bean
    Binding bing1(Queue queue1, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue1).to(topicExchange).with("topic.demo");
    }

    /**
     * topicChange 与 queue1 路由规则 topic.demo
     */
    @Bean
    Binding bing2(Queue queue2, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue2).to(topicExchange).with("topic.#");
    }


    /**
     * 死信队列
     */
    @Bean
    Queue deathQueue() {
        Map<String, Object> args = new HashMap<>();
        // 消息过期转换的消息键
        args.put("x-dead-letter-exchange", "deathExchange");
        // 真正消费队列对应的消费键
        args.put("x-dead-letter-routing-key", "consumer");
        return new Queue("deathQueue", true, false, false, args);
    }

    /**
     * 死信交换机
     */
    @Bean
    DirectExchange deathExchange() {
        return new DirectExchange("deathExchange", true, false);
    }

    /**
     * 定时任务执行的队列
     */
    @Bean
    Queue consumerQueue() {
        return new Queue("consumerQueue", true);
    }

    @Bean
    Binding bindingDeathQueue(Queue deathQueue, DirectExchange deathExchange) {
        return BindingBuilder.bind(deathQueue).to(deathExchange).with("death");
    }

    @Bean
    Binding bindingConsumerQueue(Queue consumerQueue, DirectExchange deathExchange) {
        return BindingBuilder.bind(consumerQueue).to(deathExchange).with("consumer");
    }

}

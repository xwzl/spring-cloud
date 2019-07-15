package com.java.rabbit.message.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuweizhi
 * @since 2019/07/14 12:50
 */
@Configuration
public class DemoRabbitConfig {

    /**
     * 创建 cat 队列，创建队列可以通过代码或者 rabbitmq 服务端创建，本实例通过代码创建队列；
     */
    @Bean
    Queue cat() {
        return new Queue("cat");
    }

    /**
     * 创建 animal Topic交换机，此交换机支持严格匹配和模糊匹配，最常用;其它交换机类别和差异见文档
     */
    @Bean
    TopicExchange animal() {
        return new TopicExchange("animal");
    }

    /**
     * 绑定 cat 和 animal 路由关系
     */
    @Bean
    Binding bindingAnimal(Queue cat, TopicExchange animal) {
        return BindingBuilder.bind(cat).to(animal).with("cat");
    }

}

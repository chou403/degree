package com.second.main.config.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/11/27
 * {@code @description} rabbit mq config
 */
@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.core-queue-name}")
    String queueName;

    @Value("${rabbitmq.core-exchange}")
    String exchangeKey;

    @Value("${rabbitmq.core-routing}")
    String routingKey;

    @Bean
    public Queue initCoreQueue() {
        return new Queue(queueName);
    }

    /**
     * DirectExchange: RabbitMQ的默认交换机，直接使用 routingKey 精确匹配队列
     * TopicExchange：按规则转发消息，是交换机中最灵活的一个。也是最常用的一个
     * 支持使用通配符*、#。*号只能向后多匹配一层路径。#号可以向后匹配多层路径。
     * HeadersExchange：根据请求消息中设置的 header attribute 参数类型来匹配的（和routingKey没有关系）
     * FanoutExchange：转发消息到所有绑定队列（广播模式，和routingKey没有关系）
     */
    @Bean
    public TopicExchange coreExchange() {
        return new TopicExchange(exchangeKey);
    }

//    @Bean
//    public HeadersExchange creditBankExchange() {
//        return new HeadersExchange("creditBankExchange");
//    }

//    @Bean
//    public Binding bindingCreditAExchange(Queue creditBankQueue, HeadersExchange creditBankExchange) {
//        Map<String,Object> headerValues = new HashMap<>();
//        headerValues.put("type", "cash");
//        headerValues.put("aging", "fast");
//        // whereAll 完全匹配，whereAny 部分匹配
//        return BindingBuilder.bind(creditBankQueue).to(creditBankExchange).whereAll(headerValues).match();
//    }

//    @Bean
//    public FanoutExchange reportExchange() {
//        return new FanoutExchange("reportExchange");
//    }
//
//    @Bean
//    public Binding bindingReportExchange(Queue reportQueue, FanoutExchange reportExchange) {
//        return BindingBuilder.bind(reportQueue).to(reportExchange);
//    }

    @Bean
    public Binding bindingCoreExchange(Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(routingKey);
    }
}

package com.second.main.config.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/11/27
 * {@code @description} rabbit mq producer
 */
@Slf4j
@Component
public class RabbitCoreProducer {

    @Value("${rabbitmq.core-exchange}")
    String exchangeKey;

    @Value("${rabbitmq.core-routing}")
    String routingKey;

    private final AmqpTemplate amqpTemplate;

    public RabbitCoreProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendNotification(Object obj) {
        log.info("api.core send message:{}", obj);
        this.amqpTemplate.convertAndSend(exchangeKey, routingKey, obj);
    }

    public void sendNotification(String routingKey, Object obj) {
        log.info("api.core send message:{}", obj);
        this.amqpTemplate.convertAndSend(exchangeKey, routingKey, obj);
    }
}

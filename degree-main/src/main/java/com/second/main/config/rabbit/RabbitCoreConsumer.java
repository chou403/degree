package com.second.main.config.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/11/27
 * {@code @description} rabbit mq consumer
 */
@Slf4j
//@Component
public class RabbitCoreConsumer {

    @RabbitHandler
    @RabbitListener(queues = "${rabbitmq.core-queue-name}")
    public void receiveRabbitMsg(String msg) {
        log.info("api.core receive message:{}", msg);
    }
}

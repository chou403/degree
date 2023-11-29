package com.second.main.config.kafka;

import com.second.common.utils.JsonHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/11/28
 * {@code @description} kafkaProducer
 */
@Slf4j
@Component
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 普通 不开启事务
     */
    public void sendNormalMessage(Object obj) {
        CompletableFuture<SendResult<String, Object>> completableFuture = kafkaTemplate.send("sb_topic",
                UUID.randomUUID().toString(), JsonHelper.parseToJson(obj));

        // 执行成功回调
        completableFuture.thenAccept(result -> {
            log.info("发送成功:{}", obj);
        });

        // 执行失败回调
        completableFuture.exceptionally(e -> {
            log.error("发送失败:{}", obj, e);
            return null;
        });

    }

    /**
     * 开启事务
     */
    public void sendTransactionMessage(Object obj) {
        //声明事务：后面报错消息不会发出去
        kafkaTemplate.executeInTransaction(operations -> operations.send("sb_topic", obj + " test executeInTransaction"));
    }

}

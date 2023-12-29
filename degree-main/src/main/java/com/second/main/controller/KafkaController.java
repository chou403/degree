package com.second.main.controller;

import com.second.main.config.kafka.KafkaProducer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/11/29
 * {@code @description} kafka controller
 */
@Tag(name = "kafka 消息")
@RestController
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Operation(summary = "普通模式推送消息")
    @GetMapping("/kafka/normal/{message}")
    public void sendNormal(@PathVariable("message") String message) {
        kafkaProducer.sendNormalMessage(message);
    }

    @Operation(summary = "事务模式推送消息")
    @GetMapping("/kafka/transaction/{message}")
    public void sendTransaction(@PathVariable("message") String message) {
        kafkaProducer.sendTransactionMessage(message);
    }
}

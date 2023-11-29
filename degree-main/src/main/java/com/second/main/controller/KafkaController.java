package com.second.main.controller;

import com.second.main.config.kafka.KafkaProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/11/29
 * {@code @description} kafka controller
 */
@RestController
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/kafka/normal/{message}")
    public void sendNormal(@PathVariable("message") String message) {
        kafkaProducer.sendNormalMessage(message);
    }

    @GetMapping("/kafka/transaction/{message}")
    public void sendTransaction(@PathVariable("message") String message) {
        kafkaProducer.sendTransactionMessage(message);
    }
}

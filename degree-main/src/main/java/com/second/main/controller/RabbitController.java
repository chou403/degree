package com.second.main.controller;

import com.second.main.config.rabbit.RabbitCoreProducer;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/11/27
 * {@code @description} rabbit mq controller
 */
@Tag(name = "rabbit")
@RestController
public class RabbitController {

    private final RabbitCoreProducer rabbitCoreProducer;

    public RabbitController(RabbitCoreProducer rabbitCoreProducer) {
        this.rabbitCoreProducer = rabbitCoreProducer;
    }

    @GetMapping("/sendMsg")
    public void sendMsg() {
        rabbitCoreProducer.sendNotification("api.core send message");
    }

    @GetMapping("/sendUserMsg")
    public void sendUserMsg() {
        rabbitCoreProducer.sendNotification("api.core.user", "api.core.user send message");
    }

    @GetMapping("/sendUserQueryMsg")
    public void sendUserQueryMsg() {
        rabbitCoreProducer.sendNotification("api.core.user.query", "api.core.user.query send message");
    }
}

package com.second.main;

import com.second.main.config.rabbit.RabbitCoreProducer;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/11/27
 * {@code @description} rabbit api core test
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiCoreSenderTests {

    @Resource
    private RabbitCoreProducer rabbitCoreProducer;

    @Test
    public void test_msg() {
        rabbitCoreProducer.sendNotification("rabbit 生产者 消息");
    }

    @Test
    public void test_user() {
        rabbitCoreProducer.sendNotification("api.core.user", "rabbit user 消息");
    }

    @Test
    public void test_userQuery() {
        rabbitCoreProducer.sendNotification("api.core.user.query", "rabbit user query 消息");
    }
}

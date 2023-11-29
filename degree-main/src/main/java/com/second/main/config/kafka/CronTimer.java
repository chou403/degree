package com.second.main.config.kafka;

import jakarta.annotation.Resource;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Objects;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/11/28
 * {@code @description} 定时任务类
 */
//@EnableScheduling
//@Component
public class CronTimer {

    /**
     * KafkaListener注解所标注的方法并不会在IOC容器中被注册为Bean
     * 而是会被注册在KafkaListenerEndpointRegistry中，
     * 而KafkaListenerEndpointRegistry在SpringIOC中已经被注册为Bean
     **/
    @Resource
    private KafkaListenerEndpointRegistry registry;

    @Resource
    private ConsumerFactory<String, Object> consumerFactory;

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 监听器容器工厂(设置禁止KafkaListener自启动)
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> delayContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> container = new ConcurrentKafkaListenerContainerFactory<>();
        container.setReplyTemplate(kafkaTemplate);
        container.setConsumerFactory(consumerFactory);
        //禁止KafkaListener自启动
        container.setAutoStartup(false);
        return container;
    }

    /**
     * 监听器
     */
    @KafkaListener(id = "timingConsumer", topics = "sb_topic", containerFactory = "delayContainerFactory")
    public void onMessage1(ConsumerRecord<?, ?> record) {
        System.out.println("消费成功：" + record.topic() + "-" + record.partition() + "-" + record.value());
    }

    /**
     * 定时启动监听器
     */
    @Scheduled(cron = "0 42 11 * * ? ")
    public void startListener() {
        System.out.println("启动监听器...");
        // "timingConsumer"是@KafkaListener注解后面设置的监听器ID,标识这个监听器
        if (!Objects.requireNonNull(registry.getListenerContainer("timingConsumer")).isRunning()) {
            Objects.requireNonNull(registry.getListenerContainer("timingConsumer")).start();
        }
        //registry.getListenerContainer("timingConsumer").resume();
    }

    /**
     * 定时停止监听器
     */
    @Scheduled(cron = "0 45 11 * * ? ")
    public void shutDownListener() {
        System.out.println("关闭监听器...");
        Objects.requireNonNull(registry.getListenerContainer("timingConsumer")).pause();
    }
}

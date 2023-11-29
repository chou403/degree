package com.second.main.config.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/11/28
 * {@code @description} kafka consumer
 */
@Slf4j
@Component
public class KafkaConsumer {

    private final String TOPIC1 = "sb_topic";
    private final String TOPIC2 = "sb_topic2";
    private final String TOPIC3 = "sb_topic3";
    private final String TOPIC4 = "sb_topic4";
    private final String GROUP1 = "sb_group";
    private final String GROUP2 = "sb_group2";

    @KafkaListener(topics = TOPIC1)
    public void onNormalMessage(ConsumerRecord<String, Object> record) {
        log.info("简单消费:{}-{}={}", record.topic(), record.partition(), record.value());
    }

    /**
     * id：消费者ID
     * groupId：消费组ID
     * topics：监听的topic，可监听多个
     * topicPartitions：可配置更加详细的监听信息，可指定topic、parition、offset监听，手动分区。
     */
    @KafkaListener(id = "consumer2", topics = TOPIC1, groupId = GROUP1)
    public void onBatchMessage(List<ConsumerRecord<String, Object>> records) {
        log.info(">>> 批量消费一次，records.size()={}", records.size());
        for (ConsumerRecord<String, Object> record : records) {
            log.info("" + record.value());
        }
    }

    @KafkaListener(topics = TOPIC1, errorHandler = "consumerAwareErrorHandler")
    public void onMessage4(ConsumerRecord<?, ?> record) throws Exception {
        log.info("简单消费一次...");
//        throw new BizException("简单消费-模拟异常");
    }

    @KafkaListener(topics = TOPIC1, errorHandler = "consumerAwareErrorHandler")
    public void onMessage5(List<ConsumerRecord<?, ?>> records) throws Exception {
        log.info("批量消费一次...");
//        throw new BizException("批量消费-模拟异常");
    }

    /**
     * 消息过滤监听
     */
    @KafkaListener(topics = TOPIC1, containerFactory = "filterContainerFactory")
    public void onMessage6(ConsumerRecord<?, ?> record) {
        log.info("消息过滤监听:{}", record.value());
    }

    /**
     * 消息转发 从sb_topic转发到sb_topic2
     */
    @KafkaListener(topics = TOPIC1)
    @SendTo(TOPIC2)
    public String onMessage7(ConsumerRecord<?, ?> record) {
        return record.value() + "-forward message";
    }

    @KafkaListener(topics = TOPIC2)
    public void onMessage8(ConsumerRecord<?, ?> record) {
        log.info("收到sb_topic转发过来的消息：{}", record.value());
    }

    @KafkaListener(topics = TOPIC1)
    public void onMessage9(ConsumerRecord<String, Object> record, Acknowledgment ack) {
        log.info("收到消息:{}", record.value());
        // 手动确认消息
        ack.acknowledge();
    }

    /**
     * 指定一个消费者组，一个主题主题。
     *
     * @param record
     */
    @KafkaListener(topics = TOPIC1, groupId = GROUP1)
    public void simpleConsumer(ConsumerRecord<String, String> record) {
        log.info("进入simpleConsumer方法");
        log.info("分区 = {}, 偏移量 = {}, key = {}, 内容 = {},创建消息的时间戳 = {}",
                record.partition(),
                record.offset(),
                record.key(),
                record.value(),
                record.timestamp()
        );
    }

    /**
     * 指定多个主题。
     *
     * @param record
     */
    @KafkaListener(topics = {TOPIC1, TOPIC2}, groupId = GROUP1)
    public void topics(ConsumerRecord<String, String> record) {
        log.info("进入topics方法");
        log.info(
                "主题 = {},分区 = {}, 偏移量 = {}, key = {}, 内容 = {},创建消息的时间戳 = {}",
                record.topic(),
                record.partition(),
                record.offset(),
                record.key(),
                record.value(),
                record.timestamp()
        );
    }

    /**
     * 监听一个主题，且指定消费主题的哪些分区。
     * 参数详解：消费者组=GROUP1；监听主题=iphoneTopic；只消费的分区=1,2；消费者数量=2
     *
     * @param record
     */
    @KafkaListener(
            groupId = GROUP1,
            topicPartitions = {
                    @TopicPartition(topic = TOPIC1, partitions = {"1", "2"})
            },
            concurrency = "2"
    )
    public void consumeByPattern(ConsumerRecord<String, String> record) {
        log.info("consumeByPattern");
        log.info(
                "主题 = {},分区 = {}, 偏移量 = {}, key = {}, 内容 = {},创建消息的时间戳 = {}",
                record.topic(),
                record.partition(),
                record.offset(),
                record.key(),
                record.value(),
                record.timestamp()
        );
    }

    /**
     * 指定多个分区从哪个偏移量开始消费。
     * 10个线程，也就是10个消费者
     */
    @KafkaListener(
            groupId = GROUP1,
            topicPartitions = {
                    @TopicPartition(
                            topic = TOPIC2,
                            partitions = {"0", "1"},
                            partitionOffsets = {
                                    @PartitionOffset(partition = "2", initialOffset = "10"),
                                    @PartitionOffset(partition = "3", initialOffset = "0"),
                            }
                    )
            },
            concurrency = "10"
    )
    public void consumeByPartitionOffsets(ConsumerRecord<String, String> record) {
        log.info("consumeByPartitionOffsets");
        log.info(
                "主题 = {},分区 = {}, 偏移量 = {}, key = {}, 内容 = {},创建消息的时间戳 = {}",
                record.topic(),
                record.partition(),
                record.offset(),
                record.key(),
                record.value(),
                record.timestamp()
        );
    }

    /**
     * 指定多个主题。参数详解如上面的方法。
     *
     * @param record
     */
    @KafkaListener(
            groupId = GROUP1,
            topicPartitions = {
                    @TopicPartition(topic = TOPIC1, partitions = {"1", "2"}),
                    @TopicPartition(topic = TOPIC2, partitions = "1",
                            partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "5"))
            },
            concurrency = "4"
    )
    public void topics2(ConsumerRecord<String, String> record) {
        log.info("topics2");
        log.info(
                "主题 = {},分区 = {}, 偏移量 = {}, key = {}, 内容 = {},创建消息的时间戳 = {}",
                record.topic(),
                record.partition(),
                record.offset(),
                record.key(),
                record.value(),
                record.timestamp()
        );
    }

    /**
     * 指定多个消费者组。参数详解如上面的方法。
     *
     * @param record
     */
    @KafkaListeners({
            @KafkaListener(
                    groupId = GROUP1,
                    topicPartitions = {
                            @TopicPartition(topic = TOPIC1, partitions = {"1", "2"}),
                            @TopicPartition(topic = TOPIC2, partitions = "1",
                                    partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "5"))
                    },
                    concurrency = "3"
            ),
            @KafkaListener(
                    groupId = GROUP2,
                    topicPartitions = {
                            @TopicPartition(topic = TOPIC3, partitions = {"1", "2"}),
                            @TopicPartition(topic = TOPIC4, partitions = "1",
                                    partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "5"))
                    },
                    concurrency = "3"
            )
    }
    )
    public void groupIds(ConsumerRecord<String, String> record) {
        log.info("groupIds");
        log.info("内容：" + record.value());
        log.info("分区：" + record.partition());
        log.info("偏移量：" + record.offset());
        log.info("创建消息的时间戳：" + record.timestamp());
    }

    /**
     * 设置手动提交偏移量
     *
     * @param record
     */
    @KafkaListener(
            topics = TOPIC1,
            groupId = GROUP1,
            //3个消费者
            concurrency = "3"
    )
    public void setCommitType(ConsumerRecord<String, String> record, Acknowledgment ack) {
        log.info("setCommitType");
        log.info("内容：" + record.value());
        log.info("分区：" + record.partition());
        log.info("偏移量：" + record.offset());
        log.info("创建消息的时间戳：" + record.timestamp());
        ack.acknowledge();
    }


}

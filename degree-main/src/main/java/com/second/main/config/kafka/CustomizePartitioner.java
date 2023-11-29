package com.second.main.config.kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/11/28
 * {@code @description} 自定义分区策略
 */
public class CustomizePartitioner implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        // 自定义分区规则
        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        int partition = 2;
        return partitionInfos.size() % partition;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}

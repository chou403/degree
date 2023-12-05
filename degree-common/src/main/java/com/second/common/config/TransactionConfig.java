package com.second.common.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.kafka.transaction.ChainedKafkaTransactionManager;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/12/5
 * {@code @description} 事务管理器
 */
@Configuration
@EnableTransactionManagement
public class TransactionConfig {

    private final DataSource dataSource;

    private final TransactionManagerCustomizers transactionManagerCustomizers;

    public TransactionConfig(DataSource dataSource, TransactionManagerCustomizers transactionManagerCustomizers) {
        this.dataSource = dataSource;
        this.transactionManagerCustomizers = transactionManagerCustomizers;
    }

    @Bean
    @Primary
    public DataSourceTransactionManager transactionManager(DataSourceProperties properties) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(this.dataSource);
        if (this.transactionManagerCustomizers != null) {
            this.transactionManagerCustomizers.customize((TransactionManager) transactionManager);
        }
        return transactionManager;
    }

    /**
     * 处理 DataSourceTransactionManager 和 KafkaTransactionManager 事务
     */
    @Bean
    public ChainedKafkaTransactionManager chainedKafkaTransactionManager(DataSourceTransactionManager transactionManager,
                                                                         KafkaTransactionManager<?, ?> kafkaTransactionManager) {
        return new ChainedKafkaTransactionManager<>(transactionManager, kafkaTransactionManager);
    }

}

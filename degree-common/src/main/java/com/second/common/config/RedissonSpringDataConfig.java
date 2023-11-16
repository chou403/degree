package com.second.common.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/11/15
 * {@code @description} redisson 配置类
 */
@Getter
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "redisson.singleserverconfig")
public class RedissonSpringDataConfig {

    private String address;
    private int database;
    private String password;

    @Bean
    public RedissonConnectionFactory redissonConnectionFactory(RedissonClient redisson) {
        return new RedissonConnectionFactory(redisson);
    }

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() throws JsonProcessingException {
        log.debug("[RedissonSpringDataConfig][redisson]>>>> address: {}, database: {}, password: {}", address, database, password);
        Config config = new Config();
        SingleServerConfig sconfig = config.useSingleServer()
                .setAddress(address)
                .setDatabase(database);
        // 如果redis设置了密码，这里不设置密码就会报“org.redisson.client.RedisAuthRequiredException: NOAUTH Authentication required”错误。
        if (StringUtils.hasText(password)) {
            sconfig.setPassword(password);
        }
        return Redisson.create(config);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

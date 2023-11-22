package com.second.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/11/22
 * {@code @description} ${DESCRIPTION}
 */
@SpringBootApplication(scanBasePackages = {"com.second"})
public class GatewayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }
}
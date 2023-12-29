package com.second.main.workbook.springboot.importTestSelector;

import org.springframework.context.annotation.Bean;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/8/22
 * {@code @description} zoo config
 */
public class ZooConfig {
    @Bean
    public Tiger tiger() {
        return new Tiger();
    }
}

package com.second.project.springboot.importTestSelector;

import org.springframework.context.annotation.Bean;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/8/22
 * {@code @description} zoo config
 */
public class ZooConfig {
    @Bean
    public Tiger tiger() {
        return new Tiger();
    }
}

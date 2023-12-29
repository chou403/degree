package com.second.main.workbook.springboot.importTest;

import org.springframework.context.annotation.Bean;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/8/22
 * {@code @description} config a
 */
public class ConfigA {
    @Bean
    public A a() {
        return new A();
    }
}

package com.second.main.workbook.springboot.fullImport;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/8/22
 * {@code @description} MyConfigurationConfig
 */
//@Configuration
@ComponentScan("com.second.project.springboot.fullImport")
public class MyConfigurationConfig {
    @Bean
    public A a() {
        return new A();
    }

    @Bean
    public B b() {
        return new B(a());
    }
}

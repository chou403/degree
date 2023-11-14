package com.second.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication(scanBasePackages = {"com.second"})
@MapperScan("com.second.project.mapper")
public class SecondProjectServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondProjectServiceApplication.class, args);
    }

}

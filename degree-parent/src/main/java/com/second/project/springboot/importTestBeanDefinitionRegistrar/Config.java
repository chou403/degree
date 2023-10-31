package com.second.project.springboot.importTestBeanDefinitionRegistrar;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/8/23
 * {@code @description} config
 */
//@Configuration
@Import(ZooRegistrar.class)
public class Config {
}

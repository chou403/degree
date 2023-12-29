package com.second.main.workbook.springboot.importTestBeanDefinitionRegistrar;

import org.springframework.context.annotation.Import;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/8/23
 * {@code @description} config
 */
//@Configuration
@Import(ZooRegistrar.class)
public class Config {
}

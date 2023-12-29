package com.second.main.workbook.springboot.importTest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/8/22
 * {@code @description} config b
 */
@Configuration
@Import(ConfigA.class)
public class ConfigB {
}

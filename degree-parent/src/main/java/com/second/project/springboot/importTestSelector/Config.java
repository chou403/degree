package com.second.project.springboot.importTestSelector;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * {@code @author}  JSY
 * {@code @date} 2023/8/22
 * {@code @description} config
 */
//@Configuration
@Import(ZooImportSelector.class)
public class Config {
}

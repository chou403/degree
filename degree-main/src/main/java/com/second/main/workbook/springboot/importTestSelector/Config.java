package com.second.main.workbook.springboot.importTestSelector;

import org.springframework.context.annotation.Import;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/8/22
 * {@code @description} config
 */
//@Configuration
@Import(ZooImportSelector.class)
public class Config {
}

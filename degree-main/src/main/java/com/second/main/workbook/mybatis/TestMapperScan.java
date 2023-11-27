package com.second.main.workbook.mybatis;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @author} chouchou
 * {@code @date} 2023/3/28
 * {@code @className} TestMapperScan
 * {@code @description} mapper scan
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(TestImportBeanDefinitionRegistrar.class)
public @interface TestMapperScan {
    String value();
}

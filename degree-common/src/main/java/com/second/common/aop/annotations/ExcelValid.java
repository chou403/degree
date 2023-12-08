package com.second.common.aop.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/12/7
 * {@code @description}
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelValid {
    String message() default "导入有未填入的字段";
}

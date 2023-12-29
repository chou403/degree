package com.second.common.aop.annotations;

import org.apache.ibatis.mapping.SqlCommandType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/12/29
 * {@code @description} 自动设置当前时间
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface CurrentBy {

    SqlCommandType value() default SqlCommandType.INSERT;
}

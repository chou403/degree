package com.second.common.aop.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/12/1
 * {@code @description} 多线程事务注解：子事务
 * 用在被调用方(开启线程的方法)，无需传入参数
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SonTransaction {

    String value() default "";

}

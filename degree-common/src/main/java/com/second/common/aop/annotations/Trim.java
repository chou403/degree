package com.second.common.aop.annotations;

import java.lang.annotation.*;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/12/15
 * {@code @description} 去除首尾空格的注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Trim {
}

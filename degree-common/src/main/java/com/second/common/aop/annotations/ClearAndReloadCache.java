package com.second.common.aop.annotations;

import java.lang.annotation.*;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/11/10
 * {@code @description} 延时双删切面
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface ClearAndReloadCache {
    String name() default "";
}

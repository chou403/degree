package com.second.common.aop.annotations;

import java.lang.annotation.*;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/11/15
 * {@code @description} redis 限流
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRedisLimiter {

    /**
     * 限流阈值
     */
    int limit() default 1000;

    String name() default "";
}

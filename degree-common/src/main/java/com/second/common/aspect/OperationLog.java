package com.second.common.aspect;

import java.lang.annotation.*;

/**
 * {@code @author} chouchou
 * {@code @date} 2022/12/2
 * {@code @className} OperationLog
 * {@code @description} 记录操作
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {

    /**
     * 模块
     */
    String title() default "";

    /**
     * 特殊参数
     */
    String[] special() default {};

    /**
     * 操作类型
     */
    BusinessType businessType() default BusinessType.UPDATE;
}

package com.second.common.aop.annotations;

import com.second.common.bean.BusinessTypeEnum;

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
    BusinessTypeEnum businessType() default BusinessTypeEnum.UPDATE;
}

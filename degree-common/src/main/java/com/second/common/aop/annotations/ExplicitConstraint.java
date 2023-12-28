package com.second.common.aop.annotations;

import java.lang.annotation.*;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/12/28
 * {@code @description} easy excel 实体下拉框
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExplicitConstraint {
    //定义固定下拉内容
    String[] source() default {};

    //定义动态下拉内容，
    Class[] sourceClass() default {};
}

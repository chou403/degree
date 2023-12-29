package com.second.common.aop.annotations;

import java.lang.annotation.*;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/11/21
 * {@code @description} 内外网访问权限判断
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OnlyIntranetAccess {
}

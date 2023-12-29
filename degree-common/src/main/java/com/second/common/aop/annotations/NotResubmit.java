package com.second.common.aop.annotations;

import java.lang.annotation.*;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/11/15
 * {@code @description} 防重复提交注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotResubmit {

    /**
     * 延时时间 在延时多久后可以再次提交,默认8秒
     * @return 秒
     */
    int delaySeconds() default 8;

}

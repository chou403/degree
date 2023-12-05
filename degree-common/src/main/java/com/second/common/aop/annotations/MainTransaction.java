package com.second.common.aop.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/12/1
 * {@code @description} 多线程事务注解：主事务
 * 在调用方，其参数为必填，参数值为本方法中调用的方法开启的线程数，如：在这个方法中调用的方法中有2个方法用@Async注解开启了子线程，
 * 则参数为@MainTransaction(2)，另外如果未使用@MainTransaction注解，则直接已无多线程事务执行(不影响方法的单线程事务)
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MainTransaction {
    /**
     * 子线程数量
     */
    int value();
}

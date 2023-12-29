package com.second.main.workbook.proxy;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/9/13
 * {@code @description} aspect
 */
@Component
@Aspect
public class CustomNoAspect {
    @Before("execution(* com.second.main.workbook.proxy.ICustomService.test(..)))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("CustomNoAspect......");
    }
}

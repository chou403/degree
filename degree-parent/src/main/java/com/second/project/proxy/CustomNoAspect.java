package com.second.project.proxy;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/9/13
 * {@code @description} aspect
 */
@Component
@Aspect
public class CustomNoAspect {
    @Before("execution(* com.second.project.proxy.ICustomService.test(..)))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("CustomNoAspect......");
    }
}

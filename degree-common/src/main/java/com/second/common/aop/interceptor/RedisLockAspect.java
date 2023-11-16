package com.second.common.aop.interceptor;

import com.second.common.aop.annotations.NotResubmit;
import com.second.common.bean.reponse.Result;
import com.second.common.utils.RedisLockUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/11/15
 * {@code @description} redis lock 切面
 */
@Slf4j
@Aspect
@Component
public class RedisLockAspect {

    @Resource
    private RedisLockUtil redisLock;

    @Around("execution(public * *(..)) && @annotation(com.second.common.aop.annotations.NotResubmit)")
    public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
        // 获取到这个注解
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        NotResubmit lock = method.getAnnotation(NotResubmit.class);

        final String lockKey = generateKey(pjp);

        // 上锁
        final boolean success = redisLock.lock(lockKey, null, lock.delaySeconds(), TimeUnit.SECONDS);
        if (!success) {
            return Result.error("操作太频繁，稍后重试");
        }
        return pjp.proceed();
    }

    private String generateKey(ProceedingJoinPoint pjp) {
        StringBuilder sb = new StringBuilder();
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        sb.append(pjp.getTarget().getClass().getName())//类名
                .append(method.getName());//方法名
        for (Object o : pjp.getArgs()) {
            sb.append(o.toString());
        }
        return DigestUtils.md5DigestAsHex(sb.toString().getBytes(Charset.defaultCharset()));
    }

}

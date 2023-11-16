package com.second.common.aop.interceptor;

import com.google.common.collect.Lists;
import com.second.common.aop.annotations.MyRedisLimiter;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.util.List;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/11/15
 * {@code @description} redis 限流
 */
@Slf4j
@Aspect
@Component
public class RedisLimiterAspect {

    private final HttpServletResponse response;

    private final StringRedisTemplate stringRedisTemplate;

    private DefaultRedisScript<List> redisScript;

    public RedisLimiterAspect(HttpServletResponse response, StringRedisTemplate stringRedisTemplate) {
        this.response = response;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @PostConstruct
    public void init() {
        redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(List.class);
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource(("limit.lua"))));
    }

    @Pointcut("execution(public * com.second.*.*(..))")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object process(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        //使用反射获取MyRedisLimiter注解
        MyRedisLimiter myRedisLimiter = signature.getMethod().getDeclaredAnnotation(MyRedisLimiter.class);
        if (myRedisLimiter == null) {
            //正常执行方法
            return proceedingJoinPoint.proceed();
        }
        //获取注解上的参数，获取配置的速率
        double value = myRedisLimiter.limit();
        //List设置Lua的KEYS[1]
        String key = "ip:" + System.currentTimeMillis() / 1000;
        List<String> keyList = Lists.newArrayList(key);

        //List设置Lua的ARGV[1]
        List<String> argvList = Lists.newArrayList(String.valueOf(value));

        //调用Lua脚本并执行
        List result = stringRedisTemplate.execute(redisScript, keyList, String.valueOf(value));
        log.info("Lua脚本的执行结果：" + result);

        //Lua脚本返回0，表示超出流量大小，返回1表示没有超出流量大小。
        if ("0".equals(result.get(0).toString())) {
            fullBack();
            return null;
        }

        //获取到令牌，继续向下执行
        return proceedingJoinPoint.proceed();
    }

    private void fullBack() {
        response.setHeader("Content-Type", "text/html;charset=UTF8");
        try (PrintWriter writer = response.getWriter()) {
            writer.println("回退失败，请稍后阅读。。。");
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.second.common.aop.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/12/15
 * {@code @description} 去重首尾空格处理实现
 */
@Aspect
@Component
public class TrimInterceptor implements HandlerInterceptor {

    @Pointcut(value = "@annotation(com.second.common.aop.annotations.Trim)")
    public void trimAround() {

    }

    @Before("trimAround()")
    public Object before(JoinPoint proceedingJoinPoint) throws IllegalAccessException {
        System.out.println("trimAround方法执行之前");
        // 获取入参
        Object[] objects = proceedingJoinPoint.getArgs();

        if (Objects.nonNull(objects) && objects.length > 0) {
            // 处理接口传参：去除首尾空格
            for (int i = 0, len = objects.length; i < len; i++) {
                objects[i] = trimExe(objects[i]);
            }
        }
        return objects;
    }

    /**
     * 处理所有类型参数，包括基本数据类型与vo等对象，去除首尾空格
     *
     * @param object 对象
     * @return 结果
     * @throws IllegalAccessException 异常
     */
    private static Object trimExe(Object object) throws IllegalAccessException {

        // 对象为空，不做处理
        if (Objects.isNull(object)) {
            return object;
        }

        Class<? extends Object> clazz = object.getClass();

        //  非String包装类型，不做处理
        if (clazz == Boolean.class || clazz == Short.class || clazz == Integer.class || clazz == Long.class || clazz == Float.class || clazz == Double.class) {
            return object;
        }
        if (clazz == String.class) {
            return object.toString().trim();
        }
        // 字段为private时，无法修改字段值，需要反射
        Field[] fields = clazz.getDeclaredFields();

        // 没有字段
        if (Objects.isNull(fields) || fields.length == 0) {
            return object;
        }

        for (Field field : fields) {

            Class<?> filedType = field.getType();

            // 只处理字符串类型
            if (filedType != String.class) {
                continue;
            }
            // 去除private权限，变为可更改
            field.setAccessible(true);

            if (Objects.nonNull(field.get(object))) {

                // 在原有的对象上设置去除首尾空格的新值
                field.set(object, String.valueOf(field.get(object)).trim());
            }
        }

        return object;
    }

    /**
     * 后置处理
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

    }

    /**
     * 后处理回调方法
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {

    }

    /**
     * 预处理回调方法：在请求处理之前进行调用
     */
    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
        //不需要前置处理
        return false;
    }

}

package com.second.common.interceptor;

import com.second.common.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 全局异常拦截
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result BindExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
        log.error("数据不合法：{}", e.getMessage());
        e.printStackTrace();
        return Result.error(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    public Result IllegalArgumentExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
        log.error("不合法的参数：{}", e.getMessage());
        e.printStackTrace();
        return Result.error(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest request, Exception e) {
        log.error("系统异常！原因是: {}", e.getMessage());
        e.printStackTrace();
        return Result.error(e.getMessage());
    }
}

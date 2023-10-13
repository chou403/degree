package com.second.common.advice;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 异常处理
 * {@code @author}  JSY
 * {@code @date}    2023/5/5 10:46
 */
@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> throwableAdvice(Throwable error) {
        log.error("未知错误：{}", error.getMessage(), error);
        Map<String, Object> map = Maps.newHashMap();
        map.put("errorMsg", "系统异常，请稍后重试!");
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

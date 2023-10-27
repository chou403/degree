package com.second.common.advice;

import com.second.common.bean.reponse.Result;
import com.second.common.bean.reponse.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * 异常处理
 * {@code @author}  JSY
 * {@code @date}    2023/5/5 10:46
 */
@Slf4j
@ResponseBody
@RestControllerAdvice
public class RestExceptionAdvice {

    /**
     * 系统异常处理
     *
     * @param ex 异常
     */
    @ExceptionHandler(value = Throwable.class)
    public Result defaultErrorHandler(Exception ex) {
        log.error("系统异常:{}", ex.getMessage());
        return Result.error(StatusEnum.SYSTEM_ERROR);
    }

    /**
     * BindException
     *
     * @param e 异常
     */
    @ExceptionHandler(BindException.class)
    public Result handleBingException(BindException e) {
        FieldError fieldError = e.getFieldError();
        assert fieldError != null;
        String message = fieldError.getDefaultMessage();
        return Result.error(StatusEnum.SYSTEM_ERROR, message);
    }

    /**
     * 处理 @RequestBody参数校验异常
     *
     * @param e 异常信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        assert fieldError != null;
        Integer code = Integer.valueOf(Objects.requireNonNull(fieldError.getCode()));
        String message = fieldError.getDefaultMessage();
        return Result.error(code, message);
    }

    @ExceptionHandler(NumberFormatException.class)
    public Result handleNumberFormatException(NumberFormatException ex) {
        String errorMessage = "Invalid number format. Please enter a valid number.";
        return Result.error(StatusEnum.SYSTEM_ERROR, errorMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result handleIllegalArgumentException(IllegalArgumentException ex) {
        String errorMessage = "Invalid argument. Please provide a valid argument.";
        return Result.error(StatusEnum.SYSTEM_ERROR, errorMessage);
    }

}

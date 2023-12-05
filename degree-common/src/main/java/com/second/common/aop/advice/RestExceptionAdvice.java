package com.second.common.aop.advice;

import com.second.common.bean.HttpEnum;
import com.second.common.bean.reponse.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 异常处理
 * {@code @author}  chouchou
 * {@code @date}    2023/5/5 10:46
 */
@Slf4j
@ResponseBody
@RestControllerAdvice
public class RestExceptionAdvice {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<Object> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.error(ex.getMessage(), ex);
        return Result.error(HttpEnum.NOT_FOUND, "请求路径错误或者请求方式错误(method)");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<Object> noHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request) {
        if (request.getMethod().equalsIgnoreCase(RequestMethod.OPTIONS.name())) {
            return Result.success();
        }
        log.error(ex.getMessage(), ex);
        return Result.error(HttpEnum.NOT_FOUND, ex.getLocalizedMessage());
    }

    /**
     * 参数校验类异常
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public Result<Object> handleBingException(Exception ex) {
        if (ex instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
            return getErrors(bindingResult);
        } else if (ex instanceof BindException) {
            BindingResult bindingResult = ((BindException) ex).getBindingResult();
            return getErrors(bindingResult);
        }
        return Result.error(HttpEnum.SYSTEM_ERROR);
    }

    protected Result<Object> getErrors(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        String collectStr = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
        return Result.error(HttpEnum.PARAM_ERROR, collectStr);
    }

    /**
     * 运行时异常
     */
    @ExceptionHandler({NumberFormatException.class, IllegalArgumentException.class,
            NullPointerException.class})
    public Result<Object> runtimeException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return Result.error(HttpEnum.SYSTEM_ERROR, ex.getMessage());
    }

    /**
     * 自定义异常
     */
    @ExceptionHandler(BizException.class)
    public Result<Object> numberFormatException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return Result.error(HttpEnum.SYSTEM_ERROR, ex.getMessage());
    }

    /**
     * 全局异常
     */
    @ExceptionHandler(Exception.class)
    public Result<Object> unknownException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return Result.error(HttpEnum.SYSTEM_ERROR, ex.getMessage());
    }

}

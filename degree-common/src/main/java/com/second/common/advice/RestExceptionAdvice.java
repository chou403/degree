package com.second.common.advice;

import com.second.common.bean.reponse.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.lang.reflect.Field;
import java.util.List;

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
    public Result httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletResponse response, HttpServletRequest request) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return Result.error(HttpStatus.NOT_FOUND.value(), "请求路径错误或者请求方式错误(method)");
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public Result noHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request, HttpServletResponse response) {
        if (request.getMethod().equalsIgnoreCase(RequestMethod.OPTIONS.name())) {
            response.setStatus(HttpStatus.OK.value());
            return Result.success();
        }
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return Result.error(HttpStatus.NOT_FOUND.value(), ex.getLocalizedMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletResponse response, HttpServletRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sbf = new StringBuilder();
        List<ObjectError> errors = bindingResult.getAllErrors();
        for (ObjectError error : errors) {
            try {
                Field field = error.getClass().getDeclaredField("field");
                field.setAccessible(true);
                sbf.append(field.get(error)).append(" ").append(error.getDefaultMessage()).append(";");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return Result.error(HttpStatus.BAD_REQUEST.value(), sbf.toString());
    }

    @ExceptionHandler(NullPointerException.class)
    public Result nullPointerException(Exception ex, HttpServletResponse response, HttpServletRequest request) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

//    @ExceptionHandler(CommonMsgException.class)
//    public Result msgException(CommonMsgException ex, HttpServletResponse response, HttpServletRequest request) {
//        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//        return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
//    }

//    @ExceptionHandler(AuthException.class)
//    public Result authException(AuthException ex, HttpServletResponse response, HttpServletRequest request) {
//        response.setStatus(ex.getHttpStatus());
//        return Result.error(ex.getResultCode(), ex.getMessage());
//    }

//    @ExceptionHandler(HttpServerNotSupportException.class)
//    public Result tsdbException(HttpServerNotSupportException ex, HttpServletResponse response, HttpServletRequest request) {
//        response.setStatus(HttpStatus.CONFLICT.value());
//        return Result.error(HttpStatus.CONFLICT.value(), ex.getMessage());
//    }

    @ExceptionHandler(BindException.class)
    public Result handleBingException(BindException e, HttpServletResponse response, HttpServletRequest request) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        FieldError fieldError = e.getFieldError();
        assert fieldError != null;
        return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), fieldError.getDefaultMessage());
    }

    @ExceptionHandler(NumberFormatException.class)
    public Result numberFormatException(NumberFormatException ex, HttpServletResponse response, HttpServletRequest request) {
        String errorMessage = "Invalid number format. Please enter a valid number.";
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result illegalArgumentException(IllegalArgumentException ex, HttpServletResponse response, HttpServletRequest request) {
        String errorMessage = "Invalid argument. Please provide a valid argument.";
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage);
    }

    @ExceptionHandler(BizException.class)
    public Result bizExceptionAdvice(BizException ex, HttpServletResponse response, HttpServletRequest request) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result unknownException(Exception ex, HttpServletResponse response, HttpServletRequest request) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

}

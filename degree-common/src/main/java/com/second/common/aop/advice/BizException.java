package com.second.common.aop.advice;

import lombok.Getter;

import java.io.Serializable;

/**
 * {@code @author}  chou
 * {@code @date} 2023/10/30
 * {@code @description} 自定义 excepiton
 */
@Getter
public class BizException extends RuntimeException{

    private Serializable exceptionData;

    public BizException(Exception e){
        super(e);
    }

    public BizException(String msg) {
        super(msg);
    }
    public BizException(String msg,Serializable exceptionData) {
        super(msg);
        this.exceptionData = exceptionData;
    }

    public BizException(String msg, Exception e){
        super(msg, e);
    }


    public void setExceptionData(Serializable exceptionData) {
        this.exceptionData = exceptionData;
    }

}

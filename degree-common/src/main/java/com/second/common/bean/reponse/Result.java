package com.second.common.bean.reponse;

import com.alibaba.fastjson2.JSON;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 服务器返回给客户端的统一数据封装
 * {@code @author}  JSY
 * {@code @date}    2023/4/7 14:59
 */
@Data
@NoArgsConstructor
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -367310071802308298L;

    /**
     * 状态值
     */
    private Boolean status;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态信息
     */
    private String message;

    /**
     * 返回的结果
     */
    private T data;

    /**
     * 构造函数
     *
     * @param code    状态码
     * @param message 提示信息
     */
    public Result(Integer code, String message) {
        this.status = false;
        this.code = code;
        this.message = message;
    }

    /**
     * 构造函数
     *
     * @param status  状态
     * @param code    状态码
     * @param message 提示信息
     */
    public Result(Boolean status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    /**
     * 构造函数
     *
     * @param status  状态
     * @param code    状态码
     * @param message 提示信息
     * @param data  结果
     */
    public Result(Boolean status, Integer code, String message, T data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 枚举的状态码
     */
    private static final String CODE;

    /**
     * 枚举的提示信息
     */
    private static final String MESSAGE;

    /**
     * 500
     */
    private static final Integer ERROR_CODE;

    /**
     * 成功返回的状态码
     */
    private static final Integer SUCCESS_CODE;

    /**
     * 成功返回的状态信息
     */
    private static final String SUCCESS_MSG;

    static {
        // 成功返回的状态码
        CODE = "getCode";
        // 枚举的提示信息
        MESSAGE = "getMessage";
        // 异常码
        ERROR_CODE = 500;
        // 成功返回的状态码
        SUCCESS_CODE = 200;
        // 成功返回的状态信息
        SUCCESS_MSG = "操作成功";
    }

    /**
     * 操作成功
     *
     * @return 返回结果
     */
    public static Result success() {
        return new Result(true, SUCCESS_CODE, SUCCESS_MSG);
    }

    /**
     * 操作成功
     *
     * @return 返回结果
     */
    public static <T> Result success(T result) {
        return new Result(true, SUCCESS_CODE, SUCCESS_MSG, result);
    }

    /**
     * 返回异常的
     *
     * @param t   泛型枚举
     * @param <T> 泛型
     * @return 返回 result
     */
    public static <T extends Enum> Result error(T t) {
        return error(t, CODE, MESSAGE, null);
    }

    /**
     * 返回异常的
     *
     * @param t       泛型枚举
     * @param message 提示信息
     * @param <T>     泛型
     * @return 返回 result
     */
    public static <T extends Enum> Result error(T t, String message) {
        return error(t, CODE, MESSAGE, message);
    }

    /**
     * 返回异常的
     *
     * @param code 状态码
     * @return 返回 result
     */
    public static Result error(Integer code) {
        return new Result(code, null);
    }

    /**
     * 返回异常的
     *
     * @param code    状态码
     * @param message 提示信息
     * @return 返回 result
     */
    public static Result error(Integer code, String message) {
        return new Result(code, message);
    }

    /**
     * 返回异常的
     *
     * @param t          返回的枚举
     * @param codeMethod 状态的方法
     * @param msgMethod  提示信息的状态码
     * @param <T>        泛型
     * @return 返回 result 对象
     */
    public static <T extends Enum> Result error(T t, String codeMethod, String msgMethod, String message) {
        try {
            Class<?> aClass = t.getClass();
            Integer code = (Integer) aClass.getMethod(codeMethod).invoke(t);
            message = StringUtils.isEmpty(message) ? aClass.getMethod(msgMethod).invoke(t).toString() : message;
            return new Result(code, message);
        } catch (Exception e) {
            return new Result(ERROR_CODE, e.getMessage());
        }
    }

    /**
     * 重写 toString 方法
     *
     * @return 返回JSON字符串
     */
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    /**
     * toJsonString 方法
     *
     * @return 返回JSON字符串
     */
    public String toJsonString() {
        return JSON.toJSONString(this);
    }

}

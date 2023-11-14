package com.second.common.bean.reponse;

import com.second.common.util.JsonHelper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serial;
import java.io.Serializable;

/**
 * 服务器返回给客户端的统一数据封装
 * {@code @author}  chouchou
 * {@code @date}    2023/4/7 14:59
 */
@Data
@NoArgsConstructor
public class Result<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -2937589581985593565L;

    /**
     * 状态值
     */
    private Boolean success;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态信息
     */
    private String msg;

    /**
     * 返回的结果
     */
    private T data;

    /**
     * 构造函数
     *
     * @param code 状态码
     * @param msg  提示信息
     */
    public Result(Integer code, String msg) {
        this.success = false;
        this.code = code;
        this.msg = msg;
    }

    /**
     * 构造函数
     *
     * @param success 状态
     * @param code    状态码
     * @param msg     提示信息
     */
    public Result(Boolean success, Integer code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    /**
     * 构造函数
     *
     * @param success 状态
     * @param code    状态码
     * @param msg     提示信息
     * @param data    结果
     */
    public Result(Boolean success, Integer code, String msg, T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
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
    public static <T> Result<T> success() {
        return new Result<>(true, SUCCESS_CODE, SUCCESS_MSG);
    }

    /**
     * 操作成功
     *
     * @return 返回结果
     */
    public static <T> Result<T> success(T result) {
        return new Result<>(true, SUCCESS_CODE, SUCCESS_MSG, result);
    }

    /**
     * 返回异常的
     *
     * @param t   泛型枚举
     * @param <T> 泛型
     * @return 返回 result
     */
    public static <T> Result<T> error(T t) {
        return error(t, CODE, MESSAGE, null);
    }

    /**
     * 返回异常的
     *
     * @param t   泛型枚举
     * @param msg 提示信息
     * @param <T> 泛型
     * @return 返回 result
     */
    public static <T> Result<T> error(T t, String msg) {
        return error(t, CODE, MESSAGE, msg);
    }

    /**
     * 返回异常的
     *
     * @param code 状态码
     * @param <T> 泛型
     * @return 返回 result
     */
    public static <T> Result<T> error(Integer code) {
        return new Result<>(code, null);
    }

    /**
     * 返回异常的
     *
     * @param msg 提示信息
     * @param <T> 泛型
     * @return 返回 result
     */
    public static <T> Result<T> error(String msg) {
        return new Result<>(ERROR_CODE, msg);
    }

    /**
     * 返回异常的
     *
     * @param code 状态码
     * @param msg  提示信息
     * @param <T> 泛型
     * @return 返回 result
     */
    public static <T> Result<T> error(Integer code, String msg) {
        return new Result<>(code, msg);
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
    public static <T> Result<T> error(T t, String codeMethod, String msgMethod, String msg) {
        try {
            Class<?> aClass = t.getClass();
            Integer code = (Integer) aClass.getMethod(codeMethod).invoke(t);
            msg = StringUtils.isEmpty(msg) ? aClass.getMethod(msgMethod).invoke(t).toString() : msg;
            return new Result<>(code, msg);
        } catch (Exception e) {
            return new Result<>(ERROR_CODE, e.getMessage());
        }
    }

    /**
     * 重写 toString 方法
     *
     * @return 返回JSON字符串
     */
    @Override
    public String toString() {
        return JsonHelper.parseToJson(this);
    }

    /**
     * toJsonString 方法
     *
     * @return 返回JSON字符串
     */
    public String toJsonString() {
        return JsonHelper.parseToJson(this);
    }

}

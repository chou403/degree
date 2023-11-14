package com.second.common.bean;

import lombok.Getter;

/**
 * response code
 * {@code @author}  chouchou
 * {@code @date}    2023/5/4 15:25
 */
@Getter
public enum StatusEnum {
    /**
     * 成功
     */
    OK(200, "成功"),
    /**
     * 参数错误
     */
    PARAM_ERROR(400, "参数错误"),
    /**
     * 认证失败
     */
    AUTH_ERROR(401, "认证失败"),
    /**
     * 没有访问权限
     */
    NOT_PERMISSION(403, "没有访问权限"),
    /**
     * 没有登录或者登录已经过期
     */
    NOT_LOGIN(405, "没有登录或者登录已经过期"),
    /**
     * 未知错误
     */
    UNKNOWN_ERROR(499, "未知错误"),
    /**
     * 系统错误
     */
    SYSTEM_ERROR(500, "系统繁忙，请稍后再试"),
    /**
     * 操作失败
     */
    FAILED(601, "操作失败");

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

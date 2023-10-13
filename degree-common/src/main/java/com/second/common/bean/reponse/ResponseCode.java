package com.second.common.bean.reponse;

/**
 * response code
 * {@code @author}  JSY
 * {@code @date}    2023/5/4 15:25
 */
public enum ResponseCode {
    /**
     * 成功
     */
    SUCCESS(200, "成功"),
    /**
     * 认证失败
     */
    AUTH_ERROR(401, "认证失败"),
    /**
     * 系统错误
     */
    SYS_ERROR(500, "系统错误"),
    /**
     * 参数错误
     */
    PARAM_ERROR(400, "参数错误"),
    /**
     * 未知错误
     */
    UNKNOWN_ERROR(499, "未知错误"),
    /**
     * 操作失败
     */
    FAILED(601, "操作失败");

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

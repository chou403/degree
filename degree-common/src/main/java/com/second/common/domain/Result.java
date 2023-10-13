package com.second.common.domain;

import lombok.Data;

/**
 * {@code @description}响应返回类
 */
@Data
public class Result {

    public static final String SUCCEED_CODE = "SUCCEED";
    public static final String FAILED_CODE = "FAILED";

    private String code;
    private String message;
    private String detail;
    private Object data;

    /**
     * 返回正确结果
     *
     * @return
     */
    public static Result success() {
        Result result = new Result();
        result.setCode(SUCCEED_CODE);
        return result;
    }

    /**
     * 返回正确结果
     *
     * @param obj 返回数据
     * @return
     */
    public static Result success(Object obj) {
        Result result = new Result();
        result.setCode(SUCCEED_CODE);
        result.setData(obj);
        return result;
    }

    /**
     * 返回错误结果
     *
     * @return
     */
    public static Result error() {
        Result result = new Result();
        result.setCode(FAILED_CODE);
        return result;
    }

    /**
     * 返回错误结果
     *
     * @param message 错误信息
     * @return
     */
    public static Result error(String message) {
        Result result = new Result();
        result.setCode(FAILED_CODE);
        result.setMessage(message);
        return result;
    }

    /**
     * 返回错误结果
     *
     * @param message 错误信息
     * @param detail  详细信息
     * @return
     */
    public static Result error(String message, String detail) {
        Result result = new Result();
        result.setCode(FAILED_CODE);
        result.setMessage(message);
        result.setDetail(detail);
        return result;
    }

    /**
     * 结果是否错误
     *
     * @return
     */
    public boolean errored() {
        return this == null || this.getCode().equals(Result.FAILED_CODE);
    }

    /**
     * 结果是否成功
     *
     * @return
     */
    public boolean succeed() {
        return this != null && this.getCode().equals(Result.SUCCEED_CODE);
    }

}

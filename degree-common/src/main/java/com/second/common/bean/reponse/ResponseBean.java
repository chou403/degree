package com.second.common.bean.reponse;

import lombok.Data;

import java.io.Serializable;

/**
 * 服务器返回给客户端的统一数据封装
 * {@code @author}  JSY
 * {@code @date}    2023/4/7 14:59
 */
@Data
public class ResponseBean<T> implements Serializable {
    private static final long serialVersionUID = -367310071802308298L;

    /**
     * 表示服务器状态
     */
    private Boolean flag = Boolean.TRUE;

    /**
     * 给服务器返回的 status code
     */
    private Integer code = ResponseCode.SUCCESS.getCode();

    /**
     * 给服务器返回的信息
     */
    private String message = "";

    /**
     * 服务器响应的数据
     */
    private T data;
}

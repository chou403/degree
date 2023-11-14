package com.second.common.bean.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * {@code @author} chouchou
 * {@code @date} 2022/12/14
 * {@code @className} RequestData
 * {@code @description} 请求返回
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class RequestDataDTO {
    /**
     * 请求 uri
     */
    private String uri;
    /**
     * 请求方式
     */
    private String method;
}

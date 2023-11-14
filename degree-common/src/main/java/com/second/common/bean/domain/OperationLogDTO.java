package com.second.common.bean.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * {@code @author} chouchou
 * {@code @date} 2022/12/2
 * {@code @className} OperationLogDTO
 * {@code @description} 操作记录
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class OperationLogDTO {

    /**
     * 服务中心代码
     */
    private String dealerCode;
    /**
     * 标题
     */
    private String title;
    /**
     * 方法名
     */
    private String method;
    /**
     * 操作类型
     */
    private Integer businessType;
    /**
     * 请求方式
     */
    private String requestMethod;
    /**
     * 请求路径
     */
    private String requestUrl;
    /**
     * 请求参数
     */
    private String requestParams;
    /**
     * 特殊参数
     */
    private String specialParam;
    /**
     * 操作人
     */
    private String operatorName;
    /**
     * 操作人 user code
     */
    private String operatorCode;
    /**
     * 操作人 user id
     */
    private String operatorId;
}

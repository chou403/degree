package com.second.main.domains;

import lombok.Data;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/7/15
 * {@code @description} 登录 参数
 */
@Data
public class CommonLoginDTO {
    private String userCode;
    private String password;
}

package com.second.project.other.login;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * login dto
 * {@code @author}  JSY
 * {@code @date}    2023/4/12 15:58
 */
@Component
@Scope("session")
@Data
public class LoginInfoDTO {
    private String userCode;
    private String userName;
}

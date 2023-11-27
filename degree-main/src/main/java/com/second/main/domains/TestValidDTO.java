package com.second.main.domains;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * {@code @author}  chou
 * {@code @date} 2023/10/30
 * {@code @description} test valid
 */
@Data
public class TestValidDTO {
    @NotBlank(message = "code 不能为空")
    private String code;
    @NotBlank(message = "name 不能为空")
    private String name;
}

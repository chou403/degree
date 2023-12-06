package com.second.main.domains.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/12/5
 * {@code @description}
 */
@Getter
@Setter
@Schema(description = "保存用户参数")
public class UserSaveParamDTO {

    private Long id;

    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "昵称")
    private String nickName;

    @NotBlank(message = "姓名不能为空")
    @Schema(description = "姓名")
    private String name;
}

package com.second.main.domains;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/12/5
 * {@code @description}
 */
@Getter
@Setter
public class IdParam {
    @Schema(description = "ID，记录唯一标识")
    private Integer id;
}

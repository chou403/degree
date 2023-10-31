package com.second.common.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/8/21
 * {@code @description} temp data dto
 */
@Data
public class TempDataDTO {
    private String tempDataStringA;
    private String tempDataStrinB;
    private Integer tempDataInteger;
    private BigDecimal tempDataBigDeciaml;
}

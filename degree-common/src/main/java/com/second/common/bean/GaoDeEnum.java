package com.second.common.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/11/15
 * {@code @description} 高德地图枚举
 */
@Getter
@AllArgsConstructor
public enum GaoDeEnum {

    STATUS("status"),
    INT_ONE("1"),
    RE_GEO_CODE("regeocode"),
    GEO_CODES("geocodes"),
    LOCATION("location"),
    FORMATTED_ADDRESS("formatted_address"),
    RESULTS("results"),
    DISTANCE("distance");

    private final String code;

}

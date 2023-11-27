package com.second.main.workbook.mybatis.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * {@code @author} chouchou
 * {@code @date} 2023/3/27
 * {@code @className} UserMapper
 * {@code @description} user mapper
 */
public interface UserMapper {

    @Select("select 'user' ")
    String getUser();
}

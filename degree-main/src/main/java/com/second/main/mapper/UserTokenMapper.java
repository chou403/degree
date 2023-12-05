package com.second.main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.second.main.entity.UserToken;
import org.apache.ibatis.annotations.Mapper;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/7/14
 * {@code @description} login mapper
 */
@Mapper
public interface UserTokenMapper extends BaseMapper<UserToken> {
}

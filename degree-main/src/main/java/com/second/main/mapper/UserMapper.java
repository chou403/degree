package com.second.main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.second.main.domains.dto.UserSaveParamDTO;
import com.second.main.entity.UserEntity;

/**
 * {@code @author} chou401
 * @since 2023-12-06
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    void insertUserInfo(UserEntity param);

    void updateUserInfo(UserEntity param);

}

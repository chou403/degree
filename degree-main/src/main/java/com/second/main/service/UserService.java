package com.second.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.second.common.bean.reponse.Result;
import com.second.main.domains.dto.UserSaveParamDTO;
import com.second.main.entity.UserEntity;

/**
 * {@code @author} chou401
 * @since 2023-12-06
 */
public interface UserService extends IService<UserEntity> {

    Result<Object> save(UserSaveParamDTO param);

}

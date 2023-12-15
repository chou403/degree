package com.second.main.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.second.common.bean.reponse.Result;
import com.second.main.domains.dto.UserSaveParamDTO;
import com.second.main.entity.UserEntity;
import com.second.main.mapper.UserMapper;
import com.second.main.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author chouchou
 * @since 2023-12-06
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<UserEntity> list() {
        return userMapper.selectList(null);
    }

    @Override
    public Result<Object> save(UserSaveParamDTO param) {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(param, entity);
        if (entity.getUserId() == 0) {
            entity.setUserId(null);
            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());
        } else {
            entity.setUpdateTime(new Date());
        }

        userMapper.insert(entity);

        return Result.success();
    }
}

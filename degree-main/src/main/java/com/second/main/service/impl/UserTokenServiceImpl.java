package com.second.main.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.second.main.entity.UserTokenEntity;
import com.second.main.mapper.UserTokenMapper;
import com.second.main.service.UserTokenService;
import org.springframework.stereotype.Service;

/**
 * {@code @author} chou401
 * @since 2023-12-06
 */
@Service
public class UserTokenServiceImpl extends ServiceImpl<UserTokenMapper, UserTokenEntity> implements UserTokenService {

}

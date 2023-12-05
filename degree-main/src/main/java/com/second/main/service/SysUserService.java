package com.second.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.second.common.bean.reponse.Result;
import com.second.main.domains.SysUserSaveParam;
import com.second.main.entity.SysUserEntity;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author chouchou
 * @since 2023-12-05
 */
public interface SysUserService extends IService<SysUserEntity> {

    Result save(SysUserSaveParam param);
}

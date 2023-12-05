package com.second.main.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.second.common.bean.reponse.Result;
import com.second.main.domains.SysUserSaveParam;
import com.second.main.entity.SysUserEntity;
import com.second.main.mapper.SysUserMapper;
import com.second.main.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author chouchou
 * @since 2023-12-05
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {

    private final SysUserMapper sysUserMapper;

    public SysUserServiceImpl(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public List<SysUserEntity> list() {
        log.info("查询数据");
        List<SysUserEntity> list = sysUserMapper.selectList(null);
        List<SysUserEntity> list2 = sysUserMapper.selectList(null);
        return list;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Result save(SysUserSaveParam param) {
        SysUserEntity entity = new SysUserEntity();
        BeanUtils.copyProperties(param, entity);
        if (entity.getId() == 0) {
            entity.setId(null);
            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());
        } else {
            entity.setUpdateTime(new Date());
        }


        sysUserMapper.insert(entity);

        int a = 1 / 0;

        return Result.success();
    }
}

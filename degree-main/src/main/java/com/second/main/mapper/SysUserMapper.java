package com.second.main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.second.main.entity.SysUserEntity;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author chouchou
 * @since 2023-12-05
 */
@Mapper
@CacheNamespace
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

}

package com.xzh.service.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xzh.security.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author XZH
 * @date 2021年12月14日 15:17
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser selectOne(QueryWrapper<SysUser> userName);
}

package com.xzh.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzh.security.entity.SysUser;
import com.xzh.service.mapper.SysUserMapper;
import com.xzh.service.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author XZH
 * @date 2021年12月14日 15:15
 */
@Service
public class UserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements UserService {

    @Override
    public SysUser selectUserByName(String username) {
        return baseMapper.selectOne(new QueryWrapper<SysUser>().eq("user_name", username));
    }
}

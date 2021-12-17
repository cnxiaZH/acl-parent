package com.xzh.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzh.service.entity.SysRoleMenu;
import com.xzh.service.mapper.SysRoleMenuMapper;
import com.xzh.service.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XZH
 * @date 2021年12月16日 13:16
 */
@Service
public class SysRoleUserMenuImpl extends ServiceImpl<SysRoleMenuMapper,SysRoleMenu> implements SysRoleMenuService {

    @Override
    public List<SysRoleMenu> selectRoleMenuByUserId(Long userId) {
        return baseMapper.selectRoleMenuByUserId(userId);
    }
}

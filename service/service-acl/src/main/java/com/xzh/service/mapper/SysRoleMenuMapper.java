package com.xzh.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xzh.service.entity.SysRoleMenu;

import java.util.List;

/**
 * @author XZH
 * @date 2021年12月14日 15:51
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
    List<SysRoleMenu> selectRoleMenuByUserId(Long userId);
}

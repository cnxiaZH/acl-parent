package com.xzh.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzh.service.entity.SysRoleMenu;

import java.util.List;

/**
 * @author XZH
 * @date 2021年12月16日 13:15
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {
    List<SysRoleMenu> selectRoleMenuByUserId(Long userId);
}

package com.xzh.service.service.impl;

import com.xzh.security.entity.SecurityUser;
import com.xzh.security.entity.SysUser;
import com.xzh.service.entity.SysRoleMenu;
import com.xzh.service.service.SysRoleMenuService;
import com.xzh.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XZH
 * @date 2021年12月14日 15:09
 */
@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private SysRoleMenuService roleMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.selectUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<SysRoleMenu> roleMenuList = roleMenuService.selectRoleMenuByUserId(user.getId());
        List<String> perList = new ArrayList<>();
        for (SysRoleMenu menu : roleMenuList) {
            perList.add(menu.getSysMenu().getPerms());
        }
        SecurityUser securityUser = new SecurityUser();
        securityUser.setPermissionValueList(perList);

        return securityUser;
    }
}

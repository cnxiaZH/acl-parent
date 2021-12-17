package com.xzh.service.service;

import com.xzh.security.entity.SysUser;

/**
 * @author XZH
 * @date 2021年12月14日 15:14
 */
public interface UserService {

    SysUser selectUserByName(String username);

}

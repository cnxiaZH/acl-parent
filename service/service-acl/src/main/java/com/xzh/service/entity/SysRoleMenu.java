package com.xzh.service.entity;

import com.xzh.security.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author XZH
 * @date 2021年12月14日 15:51
 */
@Data
@ToString
@EqualsAndHashCode
public class SysRoleMenu {

    private Long roleId;

    private Long userId;

    private SysMenu sysMenu;

    private SysUser sysUser;

}

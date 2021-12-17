package com.xzh.service.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author XZH
 * @date 2021年12月14日 15:52
 */
@Data
@ToString
public class SysMenu {

    private Long menuId;

    private String menuName;

    private Long parentId;

    private Integer orderNum;

    private String url;

    private String target;

    private String menuType;

    private String visible;

    private String isRefresh;

    private String perms;

    private String icon;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

}

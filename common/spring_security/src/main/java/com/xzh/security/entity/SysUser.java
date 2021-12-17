package com.xzh.security.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author XZH
 * @date 2021年12月14日 14:11
 */
@Data
@ApiModel(description = "用户实体类")
public class SysUser implements Serializable {

    private static final long seriaVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("姓名")
    private String realName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("加密盐")
    private String salt;

    @ApiModelProperty("角色列表")
    private String roleIds;

    @ApiModelProperty("部门主键")
    private Long departId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("状态")
    private String state;

    @ApiModelProperty("是否删除")
    private String dataFlag;


}

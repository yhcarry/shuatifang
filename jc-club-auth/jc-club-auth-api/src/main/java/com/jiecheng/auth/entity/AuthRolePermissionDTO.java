package com.jiecheng.auth.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色权限关联表(AuthRolePermission)实体类
 *
 * @author makejava
 * @since 2024-10-09 19:23:57
 */
@Data
public class AuthRolePermissionDTO implements Serializable {
    private static final long serialVersionUID = -97874768994457232L;
    
    private Long id;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 权限id
     */
    private Long permissionId;

    /**
     * 权限id列表
     **/
    private List<Long> permissionIdList;

}


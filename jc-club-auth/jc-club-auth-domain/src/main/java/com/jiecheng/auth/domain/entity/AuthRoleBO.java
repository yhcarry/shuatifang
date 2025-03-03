package com.jiecheng.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户角色bo
 *
 * @author makejava
 * @since 2024-09-26 19:22:05
 */
@Data
public class AuthRoleBO implements Serializable {
    private static final long serialVersionUID = -87091369346758608L;
    
    private Long id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色唯一标识
     */
    private String roleKey;


}


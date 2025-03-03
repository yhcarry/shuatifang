package com.jiecheng.auth.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色dto
 * @author: CYH 
 * @date: 2024/9/26 19:26
 **/
@Data
public class AuthRoleDTO implements Serializable {
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


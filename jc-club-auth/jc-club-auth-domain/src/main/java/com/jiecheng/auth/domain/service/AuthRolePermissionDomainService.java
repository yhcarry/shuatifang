package com.jiecheng.auth.domain.service;


import com.jiecheng.auth.domain.entity.AuthPermissionBO;
import com.jiecheng.auth.domain.entity.AuthRolePermissionBO;

/**
 * 用户角色领域service
 * @author: CYH
 * @date: 2024/9/26 20:09
 **/
public interface AuthRolePermissionDomainService {


    /**
     * 新增权限
     * @author: CYH
     * @date: 2024/10/9 18:56
     **/
    Boolean add(AuthRolePermissionBO authRolePermissionBO);

}

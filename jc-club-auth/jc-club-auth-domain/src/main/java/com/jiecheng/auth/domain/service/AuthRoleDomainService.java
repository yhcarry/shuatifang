package com.jiecheng.auth.domain.service;


import com.jiecheng.auth.domain.entity.AuthRoleBO;
import com.jiecheng.auth.domain.entity.AuthUserBO;

/**
 * 用户角色领域service
 * @author: CYH
 * @date: 2024/9/26 20:09
 **/
public interface AuthRoleDomainService {


    /**
     * 新增角色
     * @author: CYH
     * @date: 2024/9/26 19:35
     **/
    Boolean add(AuthRoleBO authRoleBO);

    /**
     * 修改角色
     * @author: CYH
     * @date: 2024/9/26 20:16
     **/
    Boolean update(AuthRoleBO authRoleBO);

    /**
     * 删除角色
     * @author: CYH
     * @date: 2024/9/26 21:17
     **/
    Boolean delete(AuthRoleBO authRoleBO);
}

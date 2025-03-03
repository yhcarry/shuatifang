package com.jiecheng.auth.domain.service;


import com.jiecheng.auth.domain.entity.AuthPermissionBO;
import com.jiecheng.auth.domain.entity.AuthRoleBO;

import java.util.List;

/**
 * 用户角色领域service
 * @author: CYH
 * @date: 2024/9/26 20:09
 **/
public interface AuthPermissionDomainService {


    /**
     * 新增权限
     * @author: CYH
     * @date: 2024/10/9 18:56
     **/
    Boolean add(AuthPermissionBO authPermissionBO);

    /**
     * 修改权限
     * @author: CYH
     * @date: 2024/10/9 19:02
     **/
    Boolean update(AuthPermissionBO authPermissionBO);

    /**
     * 删除权限
     * @author: CYH
     * @date: 2024/9/26 21:17
     **/
    Boolean delete(AuthPermissionBO authPermissionBO);

    /**
     * 获取用户权限
     **/
    List<String> getPermission(String userName);
}

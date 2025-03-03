package com.jiecheng.auth.infra.basic.service;

import com.jiecheng.auth.infra.basic.entity.AuthRole;
import com.jiecheng.auth.infra.basic.entity.AuthRolePermission;

import java.util.List;


/**
 * 角色权限关联表(AuthRolePermission)表服务接口
 *
 * @author makejava
 * @since 2024-10-09 19:23:57
 */
public interface AuthRolePermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthRolePermission queryById(Long id);


    /**
     * 新增数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    AuthRolePermission insert(AuthRolePermission authRolePermission);

    /**
     * 批量新增数据
     *
     * @param authRolePermissionList 实例对象
     * @return 实例对象
     */
    int batchInsert(List<AuthRolePermission> authRolePermissionList);

    /**
     * 修改数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    AuthRolePermission update(AuthRolePermission authRolePermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 条件查询
     * @author: CYH
     * @date: 2024/10/9 21:03
     **/
    List<AuthRolePermission> queryByCondition(AuthRolePermission authRolePermission);

}

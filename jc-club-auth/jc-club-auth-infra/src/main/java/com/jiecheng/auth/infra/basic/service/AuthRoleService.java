package com.jiecheng.auth.infra.basic.service;

import com.jiecheng.auth.infra.basic.entity.AuthRole;

import java.util.List;

/**
 * (AuthRole)表服务接口
 *
 * @author makejava
 * @since 2024-09-26 19:22:05
 */
public interface AuthRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthRole queryById(Long id);

    /**
     * 新增数据
     *
     * @param authRole 实例对象
     * @return 实例对象
     */
    Integer insert(AuthRole authRole);

    /**
     * 修改数据
     *
     * @param authRole 实例对象
     * @return 实例对象
     */
    int update(AuthRole authRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据条件查询角色
     * @author: CYH
     * @date: 2024/9/26 22:53
     **/
    AuthRole queryByCondition(AuthRole authRole);

    List<AuthRole> queryByRoleList(List<Long> roleIdList);
}

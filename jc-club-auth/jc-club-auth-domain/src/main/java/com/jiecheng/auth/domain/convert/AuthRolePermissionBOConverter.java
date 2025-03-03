package com.jiecheng.auth.domain.convert;


import com.jiecheng.auth.domain.entity.AuthPermissionBO;
import com.jiecheng.auth.domain.entity.AuthRolePermissionBO;
import com.jiecheng.auth.infra.basic.entity.AuthPermission;
import com.jiecheng.auth.infra.basic.entity.AuthRolePermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author: CYH
 * @Date: 2024/7/19 18:36
 */

@Mapper
public interface AuthRolePermissionBOConverter {

    AuthRolePermissionBOConverter INSTANCE = Mappers.getMapper(AuthRolePermissionBOConverter.class);


    AuthRolePermission convertBOToRolePermisstion(AuthRolePermissionBO authRolePermissionBO);
}

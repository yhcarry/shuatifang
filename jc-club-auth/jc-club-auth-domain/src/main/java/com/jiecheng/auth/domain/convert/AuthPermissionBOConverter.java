package com.jiecheng.auth.domain.convert;


import com.jiecheng.auth.domain.entity.AuthPermissionBO;
import com.jiecheng.auth.domain.entity.AuthRoleBO;
import com.jiecheng.auth.infra.basic.entity.AuthPermission;
import com.jiecheng.auth.infra.basic.entity.AuthRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author: CYH
 * @Date: 2024/7/19 18:36
 */

@Mapper
public interface AuthPermissionBOConverter {

    AuthPermissionBOConverter INSTANCE = Mappers.getMapper(AuthPermissionBOConverter.class);


    AuthPermission convertBOToPermisstion(AuthPermissionBO authPermissionBO);
}

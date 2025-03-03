package com.jiecheng.auth.domain.convert;


import com.jiecheng.auth.domain.entity.AuthRoleBO;
import com.jiecheng.auth.domain.entity.AuthUserBO;
import com.jiecheng.auth.infra.basic.entity.AuthRole;
import com.jiecheng.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author: CYH
 * @Date: 2024/7/19 18:36
 */

@Mapper
public interface AuthRoleBOConverter {

    AuthRoleBOConverter INSTANCE = Mappers.getMapper(AuthRoleBOConverter.class);

    AuthRole convertBOToUser(AuthRoleBO authRoleBO);
}

package com.jiecheng.auth.application.convert;


import com.jiecheng.auth.entity.AuthRolePermissionDTO;
import com.jiecheng.auth.domain.entity.AuthRolePermissionBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author: CYH
 * @Date: 2024/7/19 18:36
 */

@Mapper
public interface AuthRolePermissionDTOConverter {

    AuthRolePermissionDTOConverter INSTANCE = Mappers.getMapper(AuthRolePermissionDTOConverter.class);

    AuthRolePermissionBO convertDTOToBO(AuthRolePermissionDTO authRolePermissionDTO);


}

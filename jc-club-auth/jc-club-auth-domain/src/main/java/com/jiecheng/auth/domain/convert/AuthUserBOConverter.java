package com.jiecheng.auth.domain.convert;


import com.jiecheng.auth.domain.entity.AuthUserBO;
import com.jiecheng.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author: CYH
 * @Date: 2024/7/19 18:36
 */

@Mapper
public interface AuthUserBOConverter {

    AuthUserBOConverter INSTANCE = Mappers.getMapper(AuthUserBOConverter.class);

    AuthUser convertBOToUser(AuthUserBO authUserBO);

    AuthUserBO convertUserToBO(AuthUser user);
}

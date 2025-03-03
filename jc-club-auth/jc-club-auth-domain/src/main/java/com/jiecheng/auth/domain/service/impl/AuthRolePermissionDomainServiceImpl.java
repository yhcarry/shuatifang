package com.jiecheng.auth.domain.service.impl;

import com.jiecheng.auth.common.enums.IsDeleteFlagEnum;
import com.jiecheng.auth.domain.convert.AuthPermissionBOConverter;
import com.jiecheng.auth.domain.convert.AuthRolePermissionBOConverter;
import com.jiecheng.auth.domain.entity.AuthPermissionBO;
import com.jiecheng.auth.domain.entity.AuthRolePermissionBO;
import com.jiecheng.auth.domain.service.AuthPermissionDomainService;
import com.jiecheng.auth.domain.service.AuthRolePermissionDomainService;
import com.jiecheng.auth.infra.basic.entity.AuthPermission;
import com.jiecheng.auth.infra.basic.entity.AuthRolePermission;
import com.jiecheng.auth.infra.basic.service.AuthPermissionService;
import com.jiecheng.auth.infra.basic.service.AuthRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: CYH 
 * @date: 2024/9/26 20:07
 **/

@Service
@Slf4j
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {

    @Resource
    private AuthRolePermissionService authRolePermissionService;

    @Override
    public Boolean add(AuthRolePermissionBO authRolePermissionBO) {
        List<AuthRolePermission> authRolePermissionList = new ArrayList<>();
        Long roleId = authRolePermissionBO.getRoleId();
        authRolePermissionBO.getPermissionIdList().forEach(permissionId -> {
            AuthRolePermission authRolePermission = new AuthRolePermission();
            authRolePermission.setRoleId(roleId);
            authRolePermission.setPermissionId(permissionId);
            authRolePermission.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
            authRolePermissionList.add(authRolePermission);
        });
        Integer count = authRolePermissionService.batchInsert(authRolePermissionList);
        return count > 0;
    }


}

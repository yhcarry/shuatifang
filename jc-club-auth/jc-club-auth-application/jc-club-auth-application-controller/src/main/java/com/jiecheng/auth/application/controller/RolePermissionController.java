package com.jiecheng.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;

import com.google.common.base.Preconditions;

import com.jiecheng.auth.application.convert.AuthRolePermissionDTOConverter;
import com.jiecheng.auth.entity.AuthRolePermissionDTO;
import com.jiecheng.auth.entity.Result;

import com.jiecheng.auth.domain.entity.AuthRolePermissionBO;

import com.jiecheng.auth.domain.service.AuthRolePermissionDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 角色权限Controller
 *
 * @author: CYH
 * @date: 2024/10/9 13:18
 **/
@RestController
@RequestMapping("/rolePermission/")
@Slf4j
public class RolePermissionController {

    @Resource
    private AuthRolePermissionDomainService authRolePermissionDomainService;

    /**
     * 新增角色权限关联关系
     *
     * @author: CYH
     * @date: 2024/10/9 19:27
     **/
    @RequestMapping("add")
    public Result<Boolean> add(@RequestBody AuthRolePermissionDTO authRolePermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RolePermissionController.add.dto:{}", JSON.toJSONString(authRolePermissionDTO));
            }
            Preconditions.checkArgument(!CollectionUtils.isEmpty(authRolePermissionDTO.getPermissionIdList()), "权限关联不能为空");
            Preconditions.checkNotNull(authRolePermissionDTO.getRoleId(), "角色id不能为空");
            AuthRolePermissionBO authRolePermissionBO = AuthRolePermissionDTOConverter.INSTANCE.convertDTOToBO(authRolePermissionDTO);
            return Result.ok(authRolePermissionDomainService.add(authRolePermissionBO));
        } catch (Exception e) {
            log.info("RolePermissionController.add.error:{}", e.getMessage(), e);
            return Result.fail("增加角色权限失败");
        }
    }


}

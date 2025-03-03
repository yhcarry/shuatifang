package com.jiecheng.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.gson.Gson;
import com.jiecheng.auth.common.enums.AuthUserStatusEnum;
import com.jiecheng.auth.common.enums.IsDeleteFlagEnum;
import com.jiecheng.auth.domain.constants.AuthConstant;
import com.jiecheng.auth.domain.convert.AuthUserBOConverter;
import com.jiecheng.auth.domain.entity.AuthUserBO;
import com.jiecheng.auth.domain.redis.RedisUtil;
import com.jiecheng.auth.domain.service.AuthUserDomainService;

import com.jiecheng.auth.infra.basic.entity.*;
import com.jiecheng.auth.infra.basic.service.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: CYH
 * @Date: 2024/7/16 11:12
 */

@Service
@Slf4j
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    @Resource
    private AuthUserService authUserService;

    @Resource
    private AuthUserRoleService authUserRoleService;

    @Resource
    private AuthPermissionService authPermissionService;

    @Resource
    private AuthRolePermissionService authRolePermissionService;

    @Resource
    private AuthRoleService authRoleService;

    @Resource
    private RedisUtil redisUtil;

    private String authPermissionPrefix = "auth.permission";

    private String authRolePrefix = "auth.role";

    private static final String LOGIN_PREFIX = "loginCode";

    String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCks9uaj//8yKWLAaxDjduYTD3hlOoLd0oqM3oUmiDswUgaZHuOByHur1plZvUYog99VppzMKskVLlNM2jlwyhUDgrfCEWzEd8mIM3kvQ6lCC4gObutI9+6L4zExY2LO2/U20HoVH4SNWJrAtZI3CHGfCxGjUCCe8px46IkGALi2QIDAQAB";
    String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAKSz25qP//zIpYsBrEON25hMPeGU6gt3SiozehSaIOzBSBpke44HIe6vWmVm9RiiD31WmnMwqyRUuU0zaOXDKFQOCt8IRbMR3yYgzeS9DqUILiA5u60j37ovjMTFjYs7b9TbQehUfhI1YmsC1kjcIcZ8LEaNQIJ7ynHjoiQYAuLZAgMBAAECgYEAnCaBT7qyokDA4leFsM5voaa3DIYYlyJTyErhtWKjUJO2/n7BvVf5zcemVnkUanRLZzOSXx+HQnyo6g6RpzQtqGol/wGjAFQEVvlTsc363ogQ14ph87Jr9UVftqX3Tnx5csLI+RC7B/km/wbBc98+HlGOYr0TtsaIl8PKYKon1vkCQQDsZFVArhsqYjqnKaaYUtRsxy7dFAeQ2WS1xpBzJ8k95hjdYKLptSG6a/eu3pULbXYoUV4D5jzeJkmsi1jmrEt3AkEAsl08gyYI2WcSIa6tsXMgHyRDqRoP+JAFhfMDyb/hLCMOQNMsNh79TSjMN3ZrIAZr5ppYQHtwh6795mAKGtk4LwJBAKWwAW0vW00OwouODv2u+MMxrPA57L+jEAq0nWSJW20uTPlFlYp3caeHq3f+/Lqgw3Oo7FuRUdJnRbZRiUNNOisCQBVIYswTYcLvNY6nVTpzTkXpAdnPrlAufzo4gR81shyeIjlB+WiL0hW+5H9JtgagPYfKqvUmxYqrKsiudQY3HSkCQQDeOI5RapoJmXDJ+zTk1eh+eTbt8ZAnSskW4NR2d60jgZEG1DwWWEO9+Fu7NLxLU4mo96FXfqw/4KeipdHcLSPe";

    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(AuthUserBO authUserBO) {
        //校验用户是否存在,存在直接返回true 不用做后续处理
        AuthUser existAuthUser = new AuthUser();
        existAuthUser.setUserName(authUserBO.getUserName());
        List<AuthUser> existUser = authUserService.queryByCondition(existAuthUser);
        if(existUser.size()>0){
            return true;
        }
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToUser(authUserBO);
        if(StringUtils.isNotBlank(authUser.getPassword())){
            //密码不为空 进行加密
            authUser.setPassword(SaSecureUtil.rsaEncryptByPublic(publicKey, authUser.getPassword()));//加密
            //SaSecureUtil.rsaDecryptByPrivate(privateKey, authUser.getPassword()); //解密
        }
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        Integer count = authUserService.insert(authUser);

        //建立一个初步的角色关联
        AuthRole authRole = new AuthRole();
        authRole.setRoleKey(AuthConstant.NORMAL_USER);
        AuthRole roleResult = authRoleService.queryByCondition(authRole);
        Long roleId = roleResult.getId();
        Long userId = authUser.getId();
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(userId);
        authUserRole.setRoleId(roleId);
        authUserRole.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        authUserRoleService.insert(authUserRole);

        //把当前用户的角色和权限都刷到redis中
        String roleKey = redisUtil.buildKey(authRolePrefix, authUser.getUserName());
        List<AuthRole> roleList = new ArrayList<>();
        roleList.add(authRole);
        redisUtil.set(roleKey,new Gson().toJson(roleList));

        AuthRolePermission authRolePermission = new AuthRolePermission();
        authRolePermission.setRoleId(roleId);
        List<AuthRolePermission> authRolePermissionList = authRolePermissionService.queryByCondition(authRolePermission);
        List<Long> permissionIdList = authRolePermissionList.stream().map(AuthRolePermission::getPermissionId).collect(Collectors.toList());

        //根据roleId查权限
        List<AuthPermission> permissionList = authPermissionService.queryByPermisstionIdList(permissionIdList);
        //把当前用户的角色和权限都刷到redis中
        String permissionKey = redisUtil.buildKey(authPermissionPrefix, authUser.getUserName());
        redisUtil.set(permissionKey,new Gson().toJson(permissionList));

        return count > 0;
    }

    @Override
    public Boolean update(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToUser(authUserBO);
        Integer count = authUserService.update(authUser);
        //有任何更新,都要与缓存进行同步的修改
        return count > 0;
    }

    @Override
    public Boolean delete(AuthUserBO authUserBO) {
        AuthUser authUser = new AuthUser();
        authUser.setId(authUserBO.getId());
        authUser.setIsDeleted(IsDeleteFlagEnum.DELETED.getCode());
        Integer count = authUserService.update(authUser);
        //有任何更新,都要与缓存进行同步的修改
        return count > 0;
    }

    @Override
    public SaTokenInfo doLogin(String validCode) {
        String loginKey = redisUtil.buildKey(LOGIN_PREFIX,validCode);
        String openId = redisUtil.get(loginKey);
        if (StringUtils.isBlank(openId)){
            return null;
        }
        AuthUserBO authUserBO = new AuthUserBO();
        authUserBO.setUserName(openId);
        this.register(authUserBO);
        StpUtil.login(openId);
        SaTokenInfo saTokenInfo = StpUtil.getTokenInfo();
        return saTokenInfo;
    }

    @Override
    public AuthUserBO getUserInfo(AuthUserBO authUserBO) {
        AuthUser authUser = new AuthUser();
        authUser.setUserName(authUserBO.getUserName());
        List<AuthUser> authUserList = authUserService.queryByCondition(authUser);
        if (CollectionUtils.isEmpty(authUserList)){
            return new AuthUserBO();
        }
        AuthUser user = authUserList.get(0);
        return AuthUserBOConverter.INSTANCE.convertUserToBO(user);
    }
}

package com.jiecheng.auth.domain.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.jiecheng.auth.common.enums.IsDeleteFlagEnum;
import com.jiecheng.auth.domain.convert.AuthPermissionBOConverter;
import com.jiecheng.auth.domain.convert.AuthRoleBOConverter;
import com.jiecheng.auth.domain.entity.AuthPermissionBO;
import com.jiecheng.auth.domain.entity.AuthRoleBO;
import com.jiecheng.auth.domain.redis.RedisUtil;
import com.jiecheng.auth.domain.service.AuthPermissionDomainService;
import com.jiecheng.auth.domain.service.AuthRoleDomainService;
import com.jiecheng.auth.infra.basic.entity.AuthPermission;
import com.jiecheng.auth.infra.basic.entity.AuthRole;
import com.jiecheng.auth.infra.basic.service.AuthPermissionService;
import com.jiecheng.auth.infra.basic.service.AuthRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: CYH 
 * @date: 2024/9/26 20:07
 **/

@Service
@Slf4j
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {

    @Resource
    private AuthPermissionService authPermissionService;

    @Resource
    private RedisUtil redisUtil;

    private String authPermissionPrefix = "auth.permission";

    String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCks9uaj//8yKWLAaxDjduYTD3hlOoLd0oqM3oUmiDswUgaZHuOByHur1plZvUYog99VppzMKskVLlNM2jlwyhUDgrfCEWzEd8mIM3kvQ6lCC4gObutI9+6L4zExY2LO2/U20HoVH4SNWJrAtZI3CHGfCxGjUCCe8px46IkGALi2QIDAQAB";
    String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAKSz25qP//zIpYsBrEON25hMPeGU6gt3SiozehSaIOzBSBpke44HIe6vWmVm9RiiD31WmnMwqyRUuU0zaOXDKFQOCt8IRbMR3yYgzeS9DqUILiA5u60j37ovjMTFjYs7b9TbQehUfhI1YmsC1kjcIcZ8LEaNQIJ7ynHjoiQYAuLZAgMBAAECgYEAnCaBT7qyokDA4leFsM5voaa3DIYYlyJTyErhtWKjUJO2/n7BvVf5zcemVnkUanRLZzOSXx+HQnyo6g6RpzQtqGol/wGjAFQEVvlTsc363ogQ14ph87Jr9UVftqX3Tnx5csLI+RC7B/km/wbBc98+HlGOYr0TtsaIl8PKYKon1vkCQQDsZFVArhsqYjqnKaaYUtRsxy7dFAeQ2WS1xpBzJ8k95hjdYKLptSG6a/eu3pULbXYoUV4D5jzeJkmsi1jmrEt3AkEAsl08gyYI2WcSIa6tsXMgHyRDqRoP+JAFhfMDyb/hLCMOQNMsNh79TSjMN3ZrIAZr5ppYQHtwh6795mAKGtk4LwJBAKWwAW0vW00OwouODv2u+MMxrPA57L+jEAq0nWSJW20uTPlFlYp3caeHq3f+/Lqgw3Oo7FuRUdJnRbZRiUNNOisCQBVIYswTYcLvNY6nVTpzTkXpAdnPrlAufzo4gR81shyeIjlB+WiL0hW+5H9JtgagPYfKqvUmxYqrKsiudQY3HSkCQQDeOI5RapoJmXDJ+zTk1eh+eTbt8ZAnSskW4NR2d60jgZEG1DwWWEO9+Fu7NLxLU4mo96FXfqw/4KeipdHcLSPe";

    @Override
    public Boolean add(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.convertBOToPermisstion(authPermissionBO);
        authPermission.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        Integer count = authPermissionService.insert(authPermission);
        return count > 0;
    }

    @Override
    public Boolean update(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.convertBOToPermisstion(authPermissionBO);
        Integer count = authPermissionService.update(authPermission);
        return count > 0;
    }

    @Override
    public Boolean delete(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = new AuthPermission();
        authPermission.setId(authPermissionBO.getId());
        authPermission.setIsDeleted(IsDeleteFlagEnum.DELETED.getCode());
        Integer count = authPermissionService.update(authPermission);
        return count > 0;
    }

    @Override
    public List<String> getPermission(String userName) {
        String permissionKey = redisUtil.buildKey(authPermissionPrefix, userName);
        String permissionValue = redisUtil.get(permissionKey);
        if (StringUtils.isBlank(permissionValue)){
            return Collections.emptyList();
        }
        List<AuthPermission> permissionList = new Gson().fromJson(permissionValue, new TypeToken<List<AuthPermission>>() {
        }.getType());
        List<String> authList = permissionList.stream().map(AuthPermission::getPermissionKey).collect(Collectors.toList());
        return authList;
    }

}

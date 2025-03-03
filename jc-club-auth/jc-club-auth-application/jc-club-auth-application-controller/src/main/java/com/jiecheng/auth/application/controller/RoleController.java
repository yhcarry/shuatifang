package com.jiecheng.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.Preconditions;
import com.jiecheng.auth.application.convert.AuthRoleDTOConverter;
import com.jiecheng.auth.entity.AuthRoleDTO;
import com.jiecheng.auth.domain.entity.AuthRoleBO;
import com.jiecheng.auth.domain.service.AuthRoleDomainService;
import com.jiecheng.auth.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/role/")
@Slf4j
public class RoleController {

    @Resource
    private AuthRoleDomainService authRoleDomainService;

    /**
     * 新增角色
     * @author: CYH
     * @date: 2024/9/26 19:25
     **/
    @RequestMapping("add")
    public Result<Boolean> add(@RequestBody AuthRoleDTO authRoleDTO) {
        try{
            if (log.isInfoEnabled()){
                log.info("RoleController.add.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleKey()),"角色标识不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleName()),"角色名不能为空");
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDTOToBO(authRoleDTO);
            return Result.ok(authRoleDomainService.add(authRoleBO));
        }catch (Exception e){
            log.info("RoleController.add.error:{}",e.getMessage(),e);
            return Result.fail("增加用户角色失败");
        }
    }

    /**
     * 修改角色
     * @author: CYH
     * @date: 2024/9/26 20:14
     **/
    @RequestMapping("update")
    public Result<Boolean> update(@RequestBody AuthRoleDTO authRoleDTO) {
        try{
            if (log.isInfoEnabled()){
                log.info("RoleController.update.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkNotNull(authRoleDTO.getId(),"角色id不能为空");
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDTOToBO(authRoleDTO);
            return Result.ok(authRoleDomainService.update(authRoleBO));
        }catch (Exception e){
            log.info("RoleController.update.error:{}",e.getMessage(),e);
            return Result.fail("更新用户角色失败");
        }
    }

    /**
     * 删除角色
     * @author: CYH
     * @date: 2024/9/26 21:15
     **/
    @RequestMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthRoleDTO authRoleDTO) {
        try{
            if (log.isInfoEnabled()){
                log.info("RoleController.delete.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDTOToBO(authRoleDTO);
            return Result.ok(authRoleDomainService.delete(authRoleBO));
        }catch (Exception e){
            log.info("RoleController.delete.error:{}",e.getMessage(),e);
            return Result.fail("删除角色失败");
        }
    }



}

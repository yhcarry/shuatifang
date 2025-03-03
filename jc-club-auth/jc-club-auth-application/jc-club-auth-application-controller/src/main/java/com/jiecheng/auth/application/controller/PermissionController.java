package com.jiecheng.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.Preconditions;
import com.jiecheng.auth.application.convert.AuthPermissionDTOConverter;
import com.jiecheng.auth.entity.AuthPermissionDTO;
import com.jiecheng.auth.domain.entity.AuthPermissionBO;
import com.jiecheng.auth.domain.service.AuthPermissionDomainService;
import com.jiecheng.auth.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;
/**
 * 权限Controller
 * @author: CYH 
 * @date: 2024/10/9 13:18
 **/
@RestController
@RequestMapping("/permission/")
@Slf4j
public class PermissionController {

    @Resource
    private AuthPermissionDomainService authPermissionDomainService;

    /**
     * 新增权限
     * @author: CYH
     * @date: 2024/10/9 19:00
     **/
    @RequestMapping("add")
    public Result<Boolean> add(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try{
            if (log.isInfoEnabled()){
                log.info("PermissionController.add.dto:{}", JSON.toJSONString(authPermissionDTO));
            }
            Preconditions.checkNotNull(authPermissionDTO.getParentId(),"权限父id不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authPermissionDTO.getName()),"权限名不能为空");
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDTOToBO(authPermissionDTO);
            return Result.ok(authPermissionDomainService.add(authPermissionBO));
        }catch (Exception e){
            log.info("PermissionController.add.error:{}",e.getMessage(),e);
            return Result.fail("增加权限失败");
        }
    }

    /**
     * 修改权限
     * @author: CYH
     * @date: 2024/10/9 19:00
     **/
    @RequestMapping("update")
    public Result<Boolean> update(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try{
            if (log.isInfoEnabled()){
                log.info("PermissionController.update.dto:{}", JSON.toJSONString(authPermissionDTO));
            }
            Preconditions.checkNotNull(authPermissionDTO.getId(),"权限id不能为空");
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDTOToBO(authPermissionDTO);
            return Result.ok(authPermissionDomainService.update(authPermissionBO));
        }catch (Exception e){
            log.info("PermissionController.update.error:{}",e.getMessage(),e);
            return Result.fail("更新权限失败");
        }
    }

    /**
     * 删除权限
     * @author: CYH
     * @date: 2024/10/9 19:05
     **/
    @RequestMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try{
            if (log.isInfoEnabled()){
                log.info("PermissionController.delete.dto:{}", JSON.toJSONString(authPermissionDTO));
            }
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDTOToBO(authPermissionDTO);
            return Result.ok(authPermissionDomainService.delete(authPermissionBO));
        }catch (Exception e){
            log.info("PermissionController.delete.error:{}",e.getMessage(),e);
            return Result.fail("删除权限失败");
        }
    }

    /**
     * 查询用户权限
     * @author: CYH
     * @date: 2024/10/9 19:05
     **/
    @RequestMapping("getPermission")
    public Result<List<String>> getPermission(String userName) {
        try{
            log.info("PermissionController.getPermission.userName:{}", userName);
            Preconditions.checkArgument(!StringUtils.isBlank(userName),"用户名id不能为空");
            return Result.ok(authPermissionDomainService.getPermission(userName));
        }catch (Exception e){
            log.info("PermissionController.getPermission.error:{}",e.getMessage(),e);
            return Result.fail("获取用户权限失败");
        }
    }



}

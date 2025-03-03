package com.jiecheng.auth.application.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.Preconditions;
import com.jiecheng.auth.application.convert.AuthUserDTOConverter;
import com.jiecheng.auth.entity.AuthUserDTO;
import com.jiecheng.auth.entity.Result;
import com.jiecheng.auth.domain.entity.AuthUserBO;
import com.jiecheng.auth.domain.service.AuthUserDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user/")
@Slf4j
public class UserController {

    @Resource
    private AuthUserDomainService authUserDomainService;


    /**
     * 用户注册
     *
     * @author: CYH
     * @date: 2024/9/20 20:27
     **/
    @RequestMapping("register")
    public Result<Boolean> register(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.register.dto:{}", JSON.toJSONString(authUserDTO));
            }
            checkUserInfo(authUserDTO);
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.register(authUserBO));
        } catch (Exception e) {
            log.info("UserController.register.error:{}", e.getMessage(), e);
            return Result.fail("注册用户失败");
        }
    }

    /**
     * 修改用户信息
     *
     * @author: CYH
     * @date: 2024/9/20 20:58
     **/
    @RequestMapping("update")
    public Result<Boolean> update(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.update.dto:{}", JSON.toJSONString(authUserDTO));
            }
            checkUserInfo(authUserDTO);
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.update(authUserBO));
        } catch (Exception e) {
            log.info("UserController.update.error:{}", e.getMessage(), e);
            return Result.fail("更新用户信息失败");
        }
    }

    /**
     * 获取用户信息
     *
     * @author: CYH
     * @date: 2024/9/20 20:58
     **/
    @RequestMapping("getUserInfo")
    public Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                //序列化影响性能，开启日志再序列化打印
                log.info("UserController.getUserInfo.dto:{}", JSON.toJSONString(authUserDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "用户名不能为空");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            AuthUserBO userInfo = authUserDomainService.getUserInfo(authUserBO);
            return Result.ok(AuthUserDTOConverter.INSTANCE.convertBOToDTO(userInfo));
        } catch (Exception e) {
            log.info("UserController.getUserInfo.error:{}", e.getMessage(), e);
            return Result.fail("获取用户信息失败");
        }
    }

    /**
     * 用户退出
     *
     * @author: CYH
     * @date: 2024/9/20 20:58
     **/
    @RequestMapping("logOut")
    public Result logOut(@RequestBody String userName) {
        try {
            log.info("UserController.logOut.dto:{}", userName);
            Preconditions.checkArgument(!StringUtils.isBlank(userName), "用户名不能为空");
            StpUtil.logout(userName);
            return Result.ok();
        } catch (Exception e) {
            log.info("UserController.logOut.error:{}", e.getMessage(), e);
            return Result.fail("用户退出失败");
        }
    }

    /**
     * 删除用户
     *
     * @author: CYH
     * @date: 2024/9/20 21:09
     **/
    @RequestMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.delete.dto:{}", JSON.toJSONString(authUserDTO));
            }
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.delete(authUserBO));
        } catch (Exception e) {
            log.info("UserController.delete.error:{}", e.getMessage(), e);
            return Result.fail("删除用户失败");
        }
    }

    /**
     * 用户启用/禁用
     *
     * @author: CYH
     * @date: 2024/9/20 21:15
     **/
    @RequestMapping("changeStatus")
    public Result<Boolean> changeStatus(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.changeStatus.dto:{}", JSON.toJSONString(authUserDTO));
            }
            Preconditions.checkNotNull(authUserDTO.getStatus(), "用户状态不能为空");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.update(authUserBO));
        } catch (Exception e) {
            log.info("UserController.changeStatus.error:{}", e.getMessage(), e);
            return Result.fail("启用/禁用用户失败");
        }
    }

    /**
     * 用户注册参数校验
     * @author: CYH
     * @date: 2024/10/15 16:48
     **/
    private void checkUserInfo(@RequestBody AuthUserDTO authUserDTO) {
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "用户名不能为空");
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getEmail()), "邮件地址不能为空");
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getPassword()), "密码不能为空");
    }

    /**
     * 登录操作
     * @author: CYH
     * @date: 2024/10/15 16:48
     **/
    @RequestMapping("doLogin")
    public Result<SaTokenInfo> doLogin(@RequestParam("validCode") String validCode) {
        try {
            //参数校验
            Preconditions.checkArgument(!StringUtils.isBlank(validCode), "验证码不能为空");
            //业务处理
            SaTokenInfo tokenInfo = authUserDomainService.doLogin(validCode);
            return Result.ok(tokenInfo);
        }catch (Exception e){
            log.info("UserController.doLogin.error:{}", e.getMessage(), e);
            return Result.fail("用户登录失败");
        }

    }

    /**
     * 是否登录
     * @author: CYH
     * @date: 2024/10/15 16:48
     **/
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

}

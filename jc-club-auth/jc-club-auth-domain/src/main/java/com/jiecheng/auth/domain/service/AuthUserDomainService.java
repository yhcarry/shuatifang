package com.jiecheng.auth.domain.service;


import cn.dev33.satoken.stp.SaTokenInfo;
import com.jiecheng.auth.domain.entity.AuthUserBO;

import java.util.List;

/**
 * 用户领域service
 * @Author: CYH
 * @Date: 2024/7/16 11:11
 */
public interface AuthUserDomainService {


    /**
     * 注册
     * @author: CYH 
     * @date: 2024/9/20 21:02
     **/
    Boolean register(AuthUserBO authUserBO);

    /**
     * 更新用户信息
     * @author: CYH 
     * @date: 2024/9/20 21:02
     **/
    Boolean update(AuthUserBO authUserBO);

    /**
     * 删除用户
     * @author: CYH
     * @date: 2024/9/20 21:11
     **/
    Boolean delete(AuthUserBO authUserBO);

    /**
     * 登录
     * @author: CYH
     * @date: 2024/10/14 13:52
     **/
    SaTokenInfo doLogin(String validCode);

    AuthUserBO getUserInfo(AuthUserBO authUserBO);
}

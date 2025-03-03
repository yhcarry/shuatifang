package com.jiecheng.club.gateway.auth;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 权限认证的配置器
 * @author cyh
 */
@Configuration
public class SaTokenConfigure {
    // 注册 Sa-Token全局过滤器 
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
            // 拦截地址 
            .addInclude("/**")    /* 拦截全部path */
            // 鉴权方法：每次访问进入 
            .setAuth(obj -> {
                System.out.println("-----前端访问path:"+ SaHolder.getRequest().getRequestPath());
                // 登录校验 -- 拦截所有路由，并排除/user/doLogin 用于开放登录 
                SaRouter.match("/auth/**", "/auth/user/doLogin", r -> StpUtil.checkRole("normal_user"));
                SaRouter.match("/oss/**", r -> StpUtil.checkLogin());
                SaRouter.match("/subject/subject/add", r -> StpUtil.checkPermission("subject:add1"));
                SaRouter.match("/subject/**", r -> StpUtil.checkLogin());
            });
    }
}

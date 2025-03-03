package com.jiecheng.subject.application.controller;

import com.jiecheng.subject.infra.entity.UserInfo;
import com.jiecheng.subject.infra.rpc.UserRpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: CYH
 * @Date: 2024/11/29 23:03
 */
@RestController
@Slf4j
@RequestMapping("/subject/feign")
public class TestFeignController {

    @Resource
    private UserRpc userRpc;

    @GetMapping("testFeign")
    public void testFeign(){
        UserInfo userInfo = userRpc.getUserInfo("oFX6I6v3uG3zF85PUyj9FGGYQeKw");
        log.info("testFeign.userInfo:{}",userInfo);
    }
}

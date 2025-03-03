package com.jiecheng.subject.common.util;


import com.jiecheng.subject.common.context.LoginContextHolder;;


public class LoginUtil {

    public static String getLoginId() {
        return LoginContextHolder.getLoginId();
    }


}

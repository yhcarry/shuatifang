package com.jiecheng.auth.application.context;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 登录上下文对象
 * @Author: CYH
 * @Date: 2024/11/8 15:27
 */
public class LoginContextHolder {
    private static final InheritableThreadLocal<Map<String,Object>> THREAD_LOCAL = new InheritableThreadLocal<>();

    public static void set(String key, Object val){
        Map<String, Object> threadLocalMap = getThreadLocalMap();
        threadLocalMap.put(key,val);
    }

    public static Object get(String key){
        Map<String, Object> threadLocalMap = getThreadLocalMap();
        return threadLocalMap.get(key);
    }


    public static String getLoginId(){
        return (String) getThreadLocalMap().get("loginId");
    }

    public static void remove(){
        THREAD_LOCAL.remove();
    }

    public static Map<String, Object> getThreadLocalMap(){
        Map<String,Object> map = THREAD_LOCAL.get();
        if (Objects.isNull(map)){
            map = new ConcurrentHashMap<>();
            THREAD_LOCAL.set(map);
        }
        return map;
    }
}

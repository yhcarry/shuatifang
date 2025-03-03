package com.jiecheng.club.gateway.filter;

/**
 * @Author: CYH
 * @Date: 2025/1/6 13:52
 */
public class Singleton {
    private volatile static Singleton instance;
    private Singleton(){

    }
    public static Singleton getInstance(){
        if(instance==null){
            synchronized(Singleton.class){
                if(instance==null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

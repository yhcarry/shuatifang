package com.jiecheng.subject.common.enums;

import lombok.Getter;

/**
 * 删除状态枚举
 * @Author: CYH
 * @Date: 2024/7/20 16:12
 */

@Getter
public enum IsDeleteFlagEnum {
    DELETED(1,"已删除"),
    UN_DELETED(0,"未删除");

    public int code;

    public String desc;

    IsDeleteFlagEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static IsDeleteFlagEnum getByCode(int codeVal){
        for(IsDeleteFlagEnum resultCodeEnum : IsDeleteFlagEnum.values()){
            if(resultCodeEnum.code == codeVal){
                return resultCodeEnum;
            }
        }
        return null;
    }
}

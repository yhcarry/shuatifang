package com.jiecheng.subject.common.enums;

import lombok.Getter;

/**
 * 分类类型枚举
 * @Author: CYH
 * @Date: 2024/7/20 16:12
 */

@Getter
public enum CategoryTypeEnum {
    PRIMARY(1,"岗位大类"),
    SECOND(0,"二级分类");

    public int code;

    public String desc;

    CategoryTypeEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static CategoryTypeEnum getByCode(int codeVal){
        for(CategoryTypeEnum resultCodeEnum : CategoryTypeEnum.values()){
            if(resultCodeEnum.code == codeVal){
                return resultCodeEnum;
            }
        }
        return null;
    }
}

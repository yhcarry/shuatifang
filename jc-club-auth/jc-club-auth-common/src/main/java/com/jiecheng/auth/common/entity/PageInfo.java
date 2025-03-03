package com.jiecheng.auth.common.entity;

import lombok.Data;

/**
 * 分页请求实体
 * @Author: CYH
 * @Date: 2024/7/31 18:22
 */
@Data
public class PageInfo {

    private Integer pageNo = 1;
    private Integer pageSize = 20;

    public Integer getPageNo(){
        if(pageNo == null || pageNo < 1){
            return 1;
        }
        return pageNo;
    }

    public Integer getPageSize(){
        if(pageSize == null || pageSize <1 ||pageSize>Integer.MAX_VALUE){
            return 20;
        }
        return pageSize;
    }
}

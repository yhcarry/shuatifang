package com.jiecheng.subject.domain.handler.subject;

import com.jiecheng.subject.common.enums.SubjectInfoTypeEnum;
import com.jiecheng.subject.domain.entity.SubjectInfoBO;
import com.jiecheng.subject.domain.entity.SubjectOptionBO;

/**
 * @Author: CYH
 * @Date: 2024/7/31 16:00
 */
public interface SubjectTypeHandler {

    /**
     * 枚举身份识别
     * @author: CYH
     * @date: 2024/7/31 16:01
     **/
    SubjectInfoTypeEnum getHandlerType();


    /**
     * 实际的题目插入
     * @author: CYH 
     * @date: 2024/7/31 16:01
     **/
    void add(SubjectInfoBO subjectInfoBO);


    SubjectOptionBO query(int subjectId);
}

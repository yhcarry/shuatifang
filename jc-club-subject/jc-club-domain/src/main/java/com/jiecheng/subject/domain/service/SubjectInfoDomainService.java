package com.jiecheng.subject.domain.service;

import com.jiecheng.subject.common.entity.PageResult;
import com.jiecheng.subject.domain.entity.SubjectInfoBO;
import com.jiecheng.subject.infra.basic.entity.SubjectInfoEs;

import java.util.List;

/**
 * @Author: CYH
 * @Date: 2024/7/16 11:11
 */
public interface SubjectInfoDomainService {

    /**
     * 新增题目
     * @author: CYH
     * @date: 2024/7/31 14:38
     **/
    void add(SubjectInfoBO subjectInfoBO);


    /**
     * 分页查询
     * @author: CYH
     * @date: 2024/7/31 20:00
     **/
    PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO);

    /**
     * 查询题目信息
     */
    SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO);

    List<SubjectInfoBO> getContributeList();

    /**
     * 全文检索
     */
    PageResult<SubjectInfoEs> getSubjectPageBySearch(SubjectInfoBO subjectInfoBO);
}

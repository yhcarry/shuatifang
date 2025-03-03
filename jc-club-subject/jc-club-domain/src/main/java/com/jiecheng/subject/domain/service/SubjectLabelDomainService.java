package com.jiecheng.subject.domain.service;

import com.jiecheng.subject.domain.entity.SubjectLabelBO;

import java.util.List;

/**
 * 题目标签领域服务
 * @Author: CYH
 * @Date: 2024/7/16 11:11
 */
public interface SubjectLabelDomainService {

    /**
     * 新增标签
     * @author: CYH 
     * @date: 2024/7/29 15:44
     **/
    Boolean add(SubjectLabelBO subjectLabelBO);

    /**
     * 更新标签
     * @author: CYH
     * @date: 2024/7/29 16:02
     **/
    Boolean update(SubjectLabelBO subjectLabelBO);

    /**
     * 删除标签
     * @author: CYH
     * @date: 2024/7/29 16:09
     **/
    Boolean delete(SubjectLabelBO subjectLabelBO);

    /**
     * 查询分类下标签
     * @author: CYH
     * @date: 2024/7/29 21:52
     **/
    List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO);
}

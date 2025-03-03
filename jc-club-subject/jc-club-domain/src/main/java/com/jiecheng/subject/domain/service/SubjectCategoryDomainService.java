package com.jiecheng.subject.domain.service;

import com.jiecheng.subject.domain.entity.SubjectCategoryBO;

import java.util.List;

/**
 * @Author: CYH
 * @Date: 2024/7/16 11:11
 */
public interface SubjectCategoryDomainService {

    void add(SubjectCategoryBO subjectCategoryBO);

    /**
     * 查询岗位大类
     * @author: CYH
     * @date: 2024/7/22 18:14
     **/
    List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO);

    /**
     * 更新分类
     * @author: CYH
     * @date: 2024/7/23 20:19
     **/
    Boolean update(SubjectCategoryBO subjectCategoryBO);


    /**
     * 删除分类
     * @author: CYH
     * @date: 2024/7/24 0:40
     **/
    Boolean delete(SubjectCategoryBO subjectCategoryBO);

    /**
     * 查询分类及标签一次性
     * @author: CYH
     * @date: 2024/10/16 18:27
     **/
    List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO);
}

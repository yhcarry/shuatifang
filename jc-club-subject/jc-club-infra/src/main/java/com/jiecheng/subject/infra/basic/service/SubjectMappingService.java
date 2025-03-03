package com.jiecheng.subject.infra.basic.service;

import com.jiecheng.subject.infra.basic.entity.SubjectMapping;

import java.util.List;


/**
 * 题目分类关系表(SubjectMapping)表服务接口
 *
 * @author makejava
 * @since 2024-07-29 21:33:39
 */
public interface SubjectMappingService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectMapping queryById(Long id);


    /**
     * 新增数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    SubjectMapping insert(SubjectMapping subjectMapping);

    /**
     * 修改数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    SubjectMapping update(SubjectMapping subjectMapping);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 查询标签id
     **/
    List<SubjectMapping> queryLabelId(SubjectMapping subjectMapping);

    /**
     * 批量新增数据
     *
     * @param subjectMappingList 实例对象
     * @return 实例对象
     */
    void batchInsert(List<SubjectMapping> subjectMappingList);
}

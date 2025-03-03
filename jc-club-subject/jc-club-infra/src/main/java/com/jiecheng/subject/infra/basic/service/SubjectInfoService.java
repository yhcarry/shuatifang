package com.jiecheng.subject.infra.basic.service;

import com.jiecheng.subject.infra.basic.entity.SubjectInfo;

import java.util.List;


/**
 * 题目信息表(SubjectInfo)表服务接口
 *
 * @author makejava
 * @since 2024-07-31 13:58:03
 */
public interface SubjectInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectInfo queryById(Long id);



    /**
     * 新增数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    SubjectInfo insert(SubjectInfo subjectInfo);

    /**
     * 修改数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    SubjectInfo update(SubjectInfo subjectInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 查询题目数量
     *
     * @param
     * @return 是否成功
     */
    int countByCondition(SubjectInfo subjectInfo, Integer categoryId, Long labelId);

    /**
     * 查询题目分页
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    List<SubjectInfo> queryPage(SubjectInfo subjectInfo,
                                Integer categoryId,
                                Long labelId,
                                int start,
                                Integer pageSize);
    Long querySubjectIdCursor(Long subjectId, Integer categoryId, Long labelId, int cursor);
}

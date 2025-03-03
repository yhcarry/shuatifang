package com.jiecheng.subject.infra.basic.service;

import com.jiecheng.subject.infra.basic.entity.SubjectJudge;

import java.util.List;


/**
 * 判断题(SubjectJudge)表服务接口
 *
 * @author makejava
 * @since 2024-07-31 13:59:18
 */
public interface SubjectJudgeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectJudge queryById(Long id);


    /**
     * 新增数据
     *
     * @param subjectJudge 实例对象
     * @return 实例对象
     */
    SubjectJudge insert(SubjectJudge subjectJudge);

    /**
     * 修改数据
     *
     * @param subjectJudge 实例对象
     * @return 实例对象
     */
    SubjectJudge update(SubjectJudge subjectJudge);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 条件查询
     * @param subjectJudge
     * @return
     */
    List<SubjectJudge> queryByCondition(SubjectJudge subjectJudge);
}

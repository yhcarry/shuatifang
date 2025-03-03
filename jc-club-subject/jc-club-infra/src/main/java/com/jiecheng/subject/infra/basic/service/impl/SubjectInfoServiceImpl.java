package com.jiecheng.subject.infra.basic.service.impl;

import com.jiecheng.subject.infra.basic.entity.SubjectInfo;
import com.jiecheng.subject.infra.basic.mapper.SubjectInfoDao;
import com.jiecheng.subject.infra.basic.service.SubjectInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题目信息表(SubjectInfo)表服务实现类
 *
 * @author makejava
 * @since 2024-07-31 13:58:03
 */
@Service("subjectInfoService")
public class SubjectInfoServiceImpl implements SubjectInfoService {
    @Resource
    private SubjectInfoDao subjectInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectInfo queryById(Long id) {
        return this.subjectInfoDao.queryById(id);
    }



    /**
     * 新增数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectInfo insert(SubjectInfo subjectInfo) {
        this.subjectInfoDao.insert(subjectInfo);
        return subjectInfo;
    }

    /**
     * 修改数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectInfo update(SubjectInfo subjectInfo) {
        this.subjectInfoDao.update(subjectInfo);
        return this.queryById(subjectInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectInfoDao.deleteById(id) > 0;
    }


    /**
     * 查询题目数据
     *
     * @param
     * @return 是否成功
     */
    @Override
    public int countByCondition(SubjectInfo subjectInfo, Integer categoryId, Long labelId) {
        return this.subjectInfoDao.countByCondition(subjectInfo, categoryId, labelId);
    }

    /**
     * 查询题目分页
     *
     * @param
     * @return 是否成功
     */
    @Override
    public List<SubjectInfo> queryPage(SubjectInfo subjectInfo, Integer categoryId, Long labelId
            , int start, Integer pageSize) {
        return this.subjectInfoDao.queryByPage(subjectInfo, categoryId, labelId, start, pageSize);
    }

    @Override
    public Long querySubjectIdCursor(Long subjectId, Integer categoryId, Long labelId, int cursor) {
        return this.subjectInfoDao.querySubjectIdCursor(subjectId, categoryId, labelId, cursor);
    }

}

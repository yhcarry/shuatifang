package com.jiecheng.subject.domain.handler.subject;

import com.alibaba.fastjson.JSON;
import com.jiecheng.subject.common.enums.IsDeleteFlagEnum;
import com.jiecheng.subject.common.enums.SubjectInfoTypeEnum;
import com.jiecheng.subject.domain.convert.BriefSubjectConverter;
import com.jiecheng.subject.domain.entity.SubjectInfoBO;
import com.jiecheng.subject.domain.entity.SubjectOptionBO;
import com.jiecheng.subject.infra.basic.entity.SubjectBrief;
import com.jiecheng.subject.infra.basic.service.SubjectBriefService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 简答题目的策略类
 * @Author: CYH
 * @Date: 2024/7/31 16:02
 */
@Component
@Slf4j
public class BriefTypeHandler implements SubjectTypeHandler{

    @Resource
    private SubjectBriefService subjectBriefService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.BRIEF;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //简答题目的插入
        SubjectBrief subjectBrief = BriefSubjectConverter.INSTANCE.convertBoToEntity(subjectInfoBO);
        subjectBrief.setSubjectId(subjectInfoBO.getId().intValue());
        subjectBrief.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        subjectBriefService.insert(subjectBrief);
    }

    @Override
    public SubjectOptionBO query(int subjectId) {
        SubjectBrief subjectBrief = new SubjectBrief();
        subjectBrief.setSubjectId(subjectId);
        SubjectBrief result = subjectBriefService.queryByCondition(subjectBrief);
        if (log.isInfoEnabled()) {
            log.info("BriefTypeHandler.query.subjectBrief:{}", JSON.toJSONString(result));
        }
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setSubjectAnswer(result.getSubjectAnswer());
        return subjectOptionBO;
    }

}

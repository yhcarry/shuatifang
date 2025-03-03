package com.jiecheng.subject.infra.basic.service;

import com.jiecheng.subject.common.entity.PageResult;
import com.jiecheng.subject.infra.basic.entity.SubjectInfoEs;

public interface SubjectEsService {

    boolean insert(SubjectInfoEs subjectInfoEs);

    PageResult<SubjectInfoEs> querySubjectList(SubjectInfoEs subjectInfoEs);

}

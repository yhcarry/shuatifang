package com.jiecheng.subject.domain.convert;

import com.jiecheng.subject.domain.entity.SubjectInfoBO;
import com.jiecheng.subject.domain.entity.SubjectOptionBO;
import com.jiecheng.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author: CYH
 * @Date: 2024/7/19 18:36
 */

@Mapper
public interface SubjectInfoConverter {

    SubjectInfoConverter INSTANCE = Mappers.getMapper(SubjectInfoConverter.class);

    SubjectInfo convertBoToInfo(SubjectInfoBO subjectInfoBO);

    SubjectInfoBO convertOptionToBo(SubjectOptionBO subjectOptionBO);

    SubjectInfoBO convertOptionAndInfoToBo(SubjectOptionBO subjectOptionBO, SubjectInfo subjectInfo);

    List<SubjectInfoBO> convertListInfoToBo(List<SubjectInfo> subjectInfoList);


}

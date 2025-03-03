package com.jiecheng.subject.domain.convert;

import com.jiecheng.subject.domain.entity.SubjectLabelBO;
import com.jiecheng.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * @Author: CYH
 * @Date: 2024/7/19 18:36
 */

@Mapper
public interface SubjectLabelConverter {

    SubjectLabelConverter INSTANCE = Mappers.getMapper(SubjectLabelConverter.class);

    SubjectLabel convertBoToLabel(SubjectLabelBO subjectLabelBO);


    List<SubjectLabelBO> convertLabelToBoList(List<SubjectLabel> labelList);
}

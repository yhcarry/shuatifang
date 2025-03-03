package com.jiecheng.subject.application.convert;

import com.jiecheng.subject.application.dto.SubjectLabelDTO;
import com.jiecheng.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 标签DTO的转换
 * @Author: CYH
 * @Date: 2024/7/19 18:36
 */

@Mapper
public interface SubjectLabelDTOConverter {

    SubjectLabelDTOConverter INSTANCE = Mappers.getMapper(SubjectLabelDTOConverter.class);

    SubjectLabelBO convertDtoToLabelBO(SubjectLabelDTO subjectLabelDTO);

    List<SubjectLabelDTO> convertBoToLabelDtoList(List<SubjectLabelBO> subjectLabelBOList);

}

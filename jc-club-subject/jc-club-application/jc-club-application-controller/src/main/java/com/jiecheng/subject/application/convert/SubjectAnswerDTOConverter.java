package com.jiecheng.subject.application.convert;

import com.jiecheng.subject.application.dto.SubjectAnswerDTO;
import com.jiecheng.subject.domain.entity.SubjectAnswerBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author: CYH
 * @Date: 2024/7/19 18:36
 */

@Mapper
public interface SubjectAnswerDTOConverter {

    SubjectAnswerDTOConverter INSTANCE = Mappers.getMapper(SubjectAnswerDTOConverter.class);


    SubjectAnswerBO convertDtoToBo(SubjectAnswerDTO subjectAnswerDTO);

    List<SubjectAnswerBO> convertListDTOToBo(List<SubjectAnswerDTO> subjectAnswerDTOList);

    List<SubjectAnswerBO> convertListDTOToBO(List<SubjectAnswerDTO> optionList);
}

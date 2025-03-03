package com.jiecheng.subject.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目答案dto
 *
 * @author makejava
 * @since 2024-07-31 13:58:03
 */
@Data
public class SubjectAnswerDTO implements Serializable {
    private static final long serialVersionUID = -22148491560791006L;
    /**
     * 答案选项标识
     */
    private Integer optionType;
    /**
     * 答案
     */
    private String optionContent;
    /**
     * 是否正确
     */
    private Integer isCorrect;




}


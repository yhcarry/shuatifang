package com.jiecheng.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目标签bo
 *
 * @author makejava
 * @since 2024-07-29 15:21:28
 */

@Data
public class SubjectLabelBO implements Serializable {
    private static final long serialVersionUID = 659093681967850858L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 标签分类
     */
    private String labelName;
    /**
     * 排序
     */
    private Integer sortNum;
    /**
     * 分类id
     **/
    private Long categoryId;


}


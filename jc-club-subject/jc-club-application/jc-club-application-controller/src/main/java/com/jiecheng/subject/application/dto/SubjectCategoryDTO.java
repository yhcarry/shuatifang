package com.jiecheng.subject.application.dto;

import com.jiecheng.subject.infra.basic.entity.SubjectLabel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目分类(SubjectCategory)实体类
 *
 * @author makejava
 * @since 2024-07-13 14:49:34
 */
@Data
public class SubjectCategoryDTO implements Serializable {
    private static final long serialVersionUID = -34191179943457366L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 分类类型
     */
    private Integer categoryType;
    /**
     * 图标连接
     */
    private String imageUrl;
    /**
     * 父级id
     */
    private Long parentId;
    /**
     * 数量
     **/
    private Integer count;
    /**
     * 标签信息
     **/
    private List<SubjectLabelDTO> labelDTOList;
}


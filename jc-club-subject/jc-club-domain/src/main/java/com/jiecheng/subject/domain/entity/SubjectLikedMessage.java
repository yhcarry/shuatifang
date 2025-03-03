package com.jiecheng.subject.domain.entity;

import com.jiecheng.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目点赞消息
 */
@Data
public class SubjectLikedMessage implements Serializable {


    /**
     * 题目id
     */
    private Long subjectId;

    /**
     * 点赞人id
     */
    private String likeUserId;

    /**
     * 点赞状态 1点赞 0不点赞
     */
    private Integer status;

}


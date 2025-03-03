package com.jiecheng.subject.application.mq;

import com.alibaba.fastjson.JSON;
import com.jiecheng.subject.domain.entity.SubjectLikedBO;
import com.jiecheng.subject.domain.service.SubjectLikedDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@RocketMQMessageListener(topic = "subject-liked", consumerGroup = "subject-liked-consumer")
@Slf4j
public class SubjectLikedConsumer implements RocketMQListener<String> {

    @Resource
    private SubjectLikedDomainService subjectLikedDomainService;

    /**
     * 点赞消息通过消费者保存到数据库
     * @param s
     */
    @Override
    public void onMessage(String s) {
        System.out.println("接受点赞mq,消息为" + s);
        SubjectLikedBO subjectLikedBO = JSON.parseObject(s, SubjectLikedBO.class);
        subjectLikedDomainService.syncLikedByMsg(subjectLikedBO);
    }

}

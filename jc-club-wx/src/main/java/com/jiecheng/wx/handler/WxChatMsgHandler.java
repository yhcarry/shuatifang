package com.jiecheng.wx.handler;

import java.util.Map;

/**
 * @Author: CYH
 * @Date: 2024/10/13 19:13
 */
public interface WxChatMsgHandler {
    WxChatMsgTypeEnum getMsgType();

    String dealMsg(Map<String, String> msgMap);
}

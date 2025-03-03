package com.jiecheng.wx.controller;

import com.jiecheng.wx.handler.WxChatMsgFactory;
import com.jiecheng.wx.handler.WxChatMsgHandler;
import com.jiecheng.wx.redis.RedisUtil;
import com.jiecheng.wx.utils.MessageUtil;
import com.jiecheng.wx.utils.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: CYH
 * @Date: 2024/10/10 20:48
 */
@RestController
@Slf4j
public class CallBackController {

    private static final String token = "ccyh";

    @Resource
    private WxChatMsgFactory wxChatMsgFactory;


    private static final String CACHE_KEY_SEPARATOR = ".";

    @RequestMapping("/test")
    public String test() {
        return "hello world";
    }

    /**
     * 回调消息校验
     *
     * @author: CYH
     * @date: 2024/10/13 15:47
     **/
    @GetMapping("/callback")
    public String callback(@RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam("echostr") String echostr) {
        log.info("get验签请求参数：signature：{}，timestamp：{}，nonce：{}，echostr：{}", signature, timestamp, nonce, echostr);
        String shaStr = SHA1.getSHA1(token, timestamp, nonce, "");
        if (signature.equals(shaStr)) {
            return echostr;
        }
        return "get验签请求参数";
    }

    @PostMapping(value = "callback", produces = "application/xml;charset=UTF-8")
    public String callback(
            @RequestBody String requestBody,
            @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam(value = "msg_signature", required = false) String msgSignature) {
        log.info("接收到微信的请求：requestBody:{},signature:{},timestamp:{},nonce:{},msgSignature:{}", requestBody, signature, timestamp, nonce, msgSignature);
        //调用MessageUtil.parseXml将Xml格式文件转化为Map
        Map<String, String> msgMap = MessageUtil.parseXml(requestBody);
        //获取消息类型和事件类型
        String msgType = msgMap.get("MsgType");
        String event = msgMap.get("Event") == null ? "" : msgMap.get("Event");
        log.info("msgType:{},event:{}", msgType, event);
        //利用StringBuilder拼接
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(msgType);
        if(!StringUtils.isEmpty(event)){
            stringBuilder.append(".");
            stringBuilder.append(event);
        }
        //StringBuilder转String
        String msgTypeKey = stringBuilder.toString();
        //调用工厂获取MsgType对应的Handler
        WxChatMsgHandler wxChatMsgHandler = wxChatMsgFactory.getHandlerByMsgType(msgTypeKey);
        //无相关Handler
        if(Objects.isNull(wxChatMsgHandler)){
            return "unknown";
        }
        //由Handler得到需要返回的消息
        String replyContent = wxChatMsgHandler.dealMsg(msgMap);
        log.info("replyContent:{}", replyContent);
        return replyContent;
    }

    /**
     * 拼接字符串
     * @author: CYH
     * @date: 2024/10/13 23:22
     **/
    public String buildKey(String... strObjs){
        return Stream.of(strObjs).collect(Collectors.joining(CACHE_KEY_SEPARATOR));
    }
}

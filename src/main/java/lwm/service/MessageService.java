package lwm.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import lombok.extern.log4j.Log4j2;
import lwm.builder.MessageBuilder;
import lwm.enums.MsgTypeEnum;
import lwm.model.EventMessage;
import lwm.model.TextMessage;
import lwm.model.WeChatMessage;
import lwm.util.ExceptionUtil;
import lwm.util.MessageUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MessageService {

    public String processRequest(HttpServletRequest request) {
        WeChatMessage msg = null;
        String xmlMessageString = "";
        try {
            //xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);

            msg = MessageBuilder.buildMessage(requestMap);

            //接收到连接,进行相应的业务处理
            //测试阶段只用文字表示类型
            TextMessage textMessage = new TextMessage();

            if (msg == null){
                textMessage.setContent("未知类型消息");
            }else{
                BeanUtils.copyProperties(msg, textMessage);
                textMessage.setMsgTypeEnum(MsgTypeEnum.TEXT);
            }

            switch (msg.getMsgTypeEnum()) {
                case TEXT://文本消息
                    textMessage.setContent("文本消息");
                    break;
                case IMAGE:
                    textMessage.setContent("图片消息");
                    break;
                case VOICE:
                    textMessage.setContent("语音消息");
                    break;
                case VIDEO:
                    textMessage.setContent("视频消息");
                    break;
                case LOCATION:
                    textMessage.setContent("位置消息");
                    break;
                case EVENT:
                    EventMessage eventMsg = (EventMessage) msg;
                    String event = eventMsg.getEvent();
                    MsgTypeEnum eventType = MsgTypeEnum.getByType(event);
                    switch (eventType){
                        case EVENT_SUBSCRIBE:
                            textMessage.setContent("订阅事件");
                            break;
                        case EVENT_UNSUBSCRIBE:
                            textMessage.setContent("取消订阅事件");
                            break;
                        case EVENT_SCAN:
                            textMessage.setContent("扫描事件");
                            break;
                        case EVENT_LOCATION:
                            textMessage.setContent("上报地理位置事件");
                            break;
                        case EVENT_CLICK:
                            textMessage.setContent("点击菜单拉取消息时的事件");
                            break;
                        case VIEW:
                            textMessage.setContent("点击菜单跳转链接时的事件");
                            break;
                        case EVENT_SCANCODE_WAIT:
                            textMessage.setContent("扫描二维码并等待事件");
                            break;
                        case EVENT_SCANCODE_PUSH:
                            textMessage.setContent("扫描二维码并推送事件");
                            break;
                        case EVENT_PIC_SYSPHOTO:
                            textMessage.setContent("调取系统自带拍照功能并发送图片的事件");
                            break;
                        case EVENT_PIC_PHOTO_OR_ALBUM:
                            textMessage.setContent("调取系统自带拍照功能或选择图片库并发送图片的事件");
                            break;
                        case EVENT_PIC_WEIXIN:
                            textMessage.setContent("选择图片库并发送图片的事件");
                            break;
                        case EVENT_LOCATION_SELECT:
                            textMessage.setContent("选择位置并上报位置的事件");
                            break;
                    }

                    break;
                default:
                    textMessage.setContent("未知类型消息");
                    break;

            }

            return MessageBuilder.buildXmlMessageString(textMessage);
        } catch (Exception e) {
            log.error("processRequest,ex:{}", ExceptionUtil.toStackTrace(e));
            return xmlMessageString;
        }
    }

}

package lwm.builder;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import lwm.enums.MsgPropertyEnum;
import lwm.enums.MsgTypeEnum;
import lwm.model.TextMessage;
import lwm.model.WeChatMessage;
import lwm.util.ExceptionUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by LWM on 2020/1/13.
 */
@Log4j2
public class MessageBuilder {

    public static WeChatMessage buildMessage(Map<String,String> requestMap){
        String msgType = requestMap.get(MsgPropertyEnum.MSG_TYPE.getPropertyName());
        if (StringUtils.isEmpty(msgType)){
            log.error("找不到[属性]对应的字段名,propertyName={}",MsgPropertyEnum.MSG_TYPE.getPropertyName());
            return null;
        }

        if (msgType.equals(MsgTypeEnum.EVENT.getType())){
            msgType = requestMap.get(MsgPropertyEnum.EVENT.getPropertyName());
        }

        if (StringUtils.isEmpty(msgType)){
            log.error("找不到[属性]对应的字段名,propertyName={}",MsgPropertyEnum.EVENT.getPropertyName());
            return null;
        }

        WeChatMessage message = null;

        try {
            for (MsgTypeEnum typeEnum : MsgTypeEnum.values()) {
                if (typeEnum.getType().equals(msgType)){
                    message = (WeChatMessage) typeEnum.getClazz().newInstance();
                    message.init(requestMap);
                    break;
                }
            }
        }catch (Exception e){
            log.error("buildMessage,ex:{}", ExceptionUtil.toStackTrace(e));
            TextMessage textMessage = new TextMessage();
            textMessage.init(requestMap);
        }

        return message;
    }

    public static String buildXmlMessageString(WeChatMessage message){
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>")
                .append("<ToUserName><![CDATA[%s]]></ToUserName>")
                .append("<FromUserName><![CDATA[%s]]></FromUserName>")
                .append("<CreateTime>%d</CreateTime>")
                .append("<MsgType><![CDATA[%s]]></MsgType>")
                .append(message.getXmlContentString())
                .append("</xml>");

        String returnMsg = String.format(sb.toString(),
                message.getFromUserName(),
                message.getToUserName(),
                System.currentTimeMillis(),
                message.getMsgTypeEnum().getType()
        );

        return returnMsg;
    }

}

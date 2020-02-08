package lwm.enums;

import lombok.Getter;

/**
 * 消息通用属性
 * Created by LWM on 2020/1/13.
 */
@Getter
public enum MsgPropertyEnum {


    MSG_TYPE("MsgType","消息类型"),

    MSG_ID("MsgId","消息id"),

    CREATE_TIME("CreateTime","时间"),

    FROM_USER_NAME("FromUserName","消息发送人"),

    TO_USER_NAME("ToUserName","消息接收人"),

    EVENT("Event","事件类型"),
    ;

    private String propertyName;

    private String desc;

    MsgPropertyEnum(String propertyName, String desc) {
        this.propertyName = propertyName;
        this.desc = desc;
    }


}

package lwm.enums;

import lombok.Getter;
import lwm.model.*;

/**
 * 消息类型枚举类
 * Created by LWM on 2020/1/5.
 */
@Getter
public enum MsgTypeEnum {


    TEXT("text","文本类型", TextMessage.class),

    IMAGE("image","图片消息", ImageMessage.class),

    VOICE("voice","语音消息", VoiceMessage.class),

    VIDEO("video","视频消息", VideoMessage.class),

    SHORT_VIDEO("shortvideo","短视频消息",VideoMessage.class),

    LOCATION("location","地理位置消息", LocationMessage.class),

    LINK("link","链接消息",LinkMessage.class),

    EVENT("event","事件推送",LinkMessage.class),

    UNKNOWN("unknown","未知类型消息",UnknownMessage.class),

    EVENT_SUBSCRIBE("subscribe","关注事件",SubscribeMessage.class),

    EVENT_UNSUBSCRIBE("unsubscribe","取消关注事件",UnsubscribeMessage.class),

    EVENT_SCAN("SCAN","扫描事件",ScanMessage.class),

    EVENT_SCANCODE_WAIT("scancode_waitmsg","扫描二维码并等待",ScanCodeWaitMessage.class),

    EVENT_SCANCODE_PUSH("scancode_push","扫描二维码并推送",ScanCodePushMessage.class),

    EVENT_PIC_SYSPHOTO("pic_sysphoto","调取系统自带拍照功能并发送图片",PicSysPhotoMessage.class),

    EVENT_PIC_PHOTO_OR_ALBUM("pic_photo_or_album","调取系统自带拍照功能或选择图片库并发送图片",PicPhotoOrAlbumMessage.class),

    EVENT_PIC_WEIXIN("pic_weixin","选择图片库并发送图片",PicWeiXinMessage.class),

    EVENT_LOCATION_SELECT("location_select","选择地理位置并上报事件",LocationSelectMessage.class),

    EVENT_LOCATION("LOCATION","上报地理位置事件",LocationEventMessage.class),

    EVENT_CLICK("CLICK","点击菜单拉取消息时的事件推送",ClickMessage.class),

    VIEW("VIEW","点击菜单跳转链接时的事件推送",ViewMessage.class),

    ;


    private String type;

    private String desc;

    private Class clazz;

    MsgTypeEnum(String type, String desc, Class clazz) {
        this.type = type;
        this.desc = desc;
        this.clazz = clazz;
    }

    public static MsgTypeEnum getByType(String type){

        for (MsgTypeEnum msgTypeEnum : values()) {
            if (msgTypeEnum.getType().equals(type)){
                return msgTypeEnum;
            }
        }

        return null;
    }
}

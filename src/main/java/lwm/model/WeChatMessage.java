package lwm.model;

import lombok.Data;
import lwm.enums.MsgPropertyEnum;
import lwm.enums.MsgTypeEnum;

import java.util.Map;

/**
 * 微信消息封装
 * Created by LWM on 2020/1/5.
 */
@Data
public abstract class WeChatMessage {

    /**
     * Content:321
     * CreateTime:1499131100
     * ToUserName:gh_8312aaefbd9d
     * FromUserName:otNX_vmmznd3uQH4Vk62BnjA3PuQ
     * MsgTypeEnum:text
     * MsgId:6438719047333164987
     */

    private String msgId;

    private MsgTypeEnum msgTypeEnum;

    private long createTime;

    private String fromUserName;

    private String toUserName;

    /**
     * 初始化对象
     * 采用模板方法
     * @param requestMap
     */
    public void init(Map<String,String> requestMap){

        initCommon(requestMap);

        initUnique(requestMap);
    }

    protected void initCommon(Map<String, String> requestMap){
        msgId = requestMap.get(MsgPropertyEnum.MSG_ID.getPropertyName());
        msgTypeEnum = MsgTypeEnum.getByType(requestMap.get(MsgPropertyEnum.MSG_TYPE.getPropertyName()));
        createTime = Long.valueOf(requestMap.get(MsgPropertyEnum.CREATE_TIME.getPropertyName()));
        fromUserName = requestMap.get(MsgPropertyEnum.FROM_USER_NAME.getPropertyName());
        toUserName = requestMap.get(MsgPropertyEnum.TO_USER_NAME.getPropertyName());
    }

    protected abstract void initUnique(Map<String, String> requestMap);

    /**
     * 消息内容部分的xml字符串
     * @return
     */
    public abstract String getXmlContentString();

}

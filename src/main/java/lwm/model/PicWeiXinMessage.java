package lwm.model;

import lombok.Data;

import java.util.Map;

/**
 * 选择系统图片库并发送图片 事件
 * Created by LWM on 2020/2/1.
 */
@Data
public class PicWeiXinMessage extends EventMessage {

    private String eventKey;

    private String sendPicsInfo;

    @Override
    protected void initUnique(Map<String, String> requestMap) {
        this.eventKey = requestMap.get("EventKey");
        this.sendPicsInfo = requestMap.get("SendPicsInfo");
    }

    @Override
    public String getXmlContentString() {
        return null;
    }
}

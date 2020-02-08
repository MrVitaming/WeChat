package lwm.model;

import lombok.Data;

import java.util.Map;

/**
 * 选择位置并发送 消息
 * Created by LWM on 2020/2/1.
 */
@Data
public class LocationSelectMessage extends EventMessage{

    private String eventKey;

    private String sendLocationInfo;

    @Override
    protected void initUnique(Map<String, String> requestMap) {
        this.eventKey = requestMap.get("EventKey");
        this.sendLocationInfo = requestMap.get("SendLocationInfo");
    }

    @Override
    public String getXmlContentString() {
        return null;
    }
}

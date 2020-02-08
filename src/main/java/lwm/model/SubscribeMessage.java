package lwm.model;

import lombok.Data;

import java.util.Map;

/**
 * Created by LWM on 2020/1/25.
 */
@Data
public class SubscribeMessage extends EventMessage{

    /**
     * 事件KEY值，qrscene_为前缀，后面为二维码的参数值
     */
    private String eventKey;

    private String ticket;

    @Override
    protected void initUnique(Map<String, String> requestMap) {
        this.eventKey = requestMap.get("EventKey");
        this.ticket = requestMap.get("Ticket");
    }

    @Override
    public String getXmlContentString() {
        return null;
    }
}

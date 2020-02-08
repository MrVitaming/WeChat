package lwm.model;

import lombok.Data;

import java.util.Map;

/**
 * 点击菜单跳转链接时的事件推送
 * Created by LWM on 2020/1/25.
 */
@Data
public class ViewMessage extends EventMessage{

    /**
     * 事件KEY值，设置的跳转URL
     */
    private String eventKey;

    @Override
    protected void initUnique(Map<String, String> requestMap) {
        this.eventKey = requestMap.get("EventKey");
    }

    @Override
    public String getXmlContentString() {
        return "";
    }
}

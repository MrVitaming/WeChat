package lwm.model;

import lombok.Data;

import java.util.Map;

/**
 * 点击菜单拉取消息时的事件推送
 * Created by LWM on 2020/1/25.
 */
@Data
public class ClickMessage extends EventMessage{

    /**
     * 事件KEY值，与自定义菜单接口中KEY值对应
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

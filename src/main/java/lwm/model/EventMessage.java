package lwm.model;

import lombok.Data;

import java.util.Map;

/**
 * Created by LWM on 2020/1/25.
 */
@Data
public abstract class EventMessage extends WeChatMessage{

    private String event;

    @Override
    protected void initCommon(Map<String, String> requestMap) {
        super.initCommon(requestMap);
        event = requestMap.get("Event");
    }
}

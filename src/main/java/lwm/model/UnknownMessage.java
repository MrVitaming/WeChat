package lwm.model;

import lombok.Data;

import java.util.Map;

/**
 * 未知消息类型
 * Created by LWM on 2020/1/13.
 */
@Data
public class UnknownMessage extends  CommonMessage{

    @Override
    protected void initUnique(Map<String, String> requestMap) {

    }

    @Override
    public String getXmlContentString() {
        return null;
    }
}

package lwm.model;

import lombok.Data;

import java.util.Map;

/**
 * 扫描二维码直接推送结果
 * Created by LWM on 2020/2/1.
 */
@Data
public class ScanCodePushMessage extends EventMessage {

    private String eventKey;

    private String scanCodeInfo;

    @Override
    protected void initUnique(Map<String, String> requestMap) {
        this.eventKey = requestMap.get("EventKey");
        this.scanCodeInfo = requestMap.get("ScanCodeInfo");
    }

    @Override
    public String getXmlContentString() {
        return null;
    }
}

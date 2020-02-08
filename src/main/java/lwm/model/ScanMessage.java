package lwm.model;

import lombok.Data;
import org.mockito.internal.util.StringUtil;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 扫描事件消息
 * Created by LWM on 2020/1/25.
 */
@Data
public class ScanMessage extends EventMessage {

    /**
     * 事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
     */
    private String eventKey;

    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    private String ticket;

    @Override
    protected void initUnique(Map<String, String> requestMap) {
        this.eventKey = requestMap.get("EventKey");
        this.ticket = requestMap.get("Ticket");
    }

    @Override
    public String getXmlContentString() {
        return "";
    }
}

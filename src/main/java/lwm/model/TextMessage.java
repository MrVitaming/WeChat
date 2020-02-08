package lwm.model;

import lombok.Data;

import java.util.Map;

/**
 * 文本消息
 * Created by LWM on 2020/1/13.
 */
@Data
public class TextMessage extends CommonMessage {

    private String content;

    @Override
    protected void initUnique(Map<String, String> requestMap) {
        content = requestMap.get("Content");
    }

    @Override
    public String getXmlContentString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<Content><![CDATA[%s]]></Content>");

        return String.format(sb.toString(),getContent());
    }
}

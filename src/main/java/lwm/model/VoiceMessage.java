package lwm.model;

import lombok.Data;

import java.util.Map;

/**
 * 语音消息
 * Created by LWM on 2020/1/13.
 */
@Data
public class VoiceMessage extends CommonMessage{

    private String mediaId;

    private String format;

    private String recognition;

    @Override
    protected void initUnique(Map<String, String> requestMap) {
        mediaId = requestMap.get("MediaId");
        format = requestMap.get("Format");
        recognition = requestMap.get("Recognition");
    }

    @Override
    public String getXmlContentString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<MediaId><![CDATA[%s]]></MediaId>")
                .append("<Format><![CDATA[%s]]></Format>")
                .append("<Recognition><![CDATA[%s]]></Recognition>");

        return String.format(sb.toString(),mediaId,format,recognition);
    }
}

package lwm.model;

import lombok.Data;

import java.util.Map;

/**
 * 视频消息
 * Created by LWM on 2020/1/13.
 */
@Data
public class VideoMessage extends WeChatMessage{

    private String mediaId;

    private String thumbMediaId;

    @Override
    protected void initUnique(Map<String, String> requestMap) {
        mediaId = requestMap.get("MediaId");
        thumbMediaId = requestMap.get("ThumbMediaId");
    }

    @Override
    public String getXmlContentString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<MediaId><![CDATA[%s]]></MediaId>")
                .append("<ThumbMediaId><![CDATA[%s]]></ThumbMediaId>");

        return String.format(sb.toString(),mediaId,thumbMediaId);

    }
}

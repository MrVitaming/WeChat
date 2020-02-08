package lwm.model;

import lombok.Data;

import java.util.Map;

/**
 * Created by LWM on 2020/1/13.
 */

@Data
public class ImageMessage extends CommonMessage {

    private String picUrl;

    private String mediaId;

    @Override
    public String getXmlContentString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<PicUrl><![CDATA[%s]]></PicUrl>")
                .append("<MediaId><![CDATA[%s]]></MediaId>");

        return String.format(sb.toString(),picUrl,mediaId);
    }

    @Override
    protected void initUnique(Map<String, String> requestMap) {
        picUrl = requestMap.get("PicUrl");
        mediaId = requestMap.get("MediaId");
    }
}

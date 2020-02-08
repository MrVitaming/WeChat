package lwm.model;

import lombok.Data;

import java.util.Map;

/**
 * 地理位置消息
 * Created by LWM on 2020/1/13.
 */

@Data
public class LocationMessage extends CommonMessage{

    private String locationX;

    private String locationY;

    private String scale;

    private String label;

    @Override
    protected void initUnique(Map<String, String> requestMap) {
        locationX = requestMap.get("Location_X");
        locationY = requestMap.get("Location_Y");
        scale = requestMap.get("Scale");
        label = requestMap.get("Label");
    }

    @Override
    public String getXmlContentString() {

        StringBuilder sb = new StringBuilder();

        sb.append("<Location_X><![CDATA[%s]]></Location_X>")
                .append("<Location_Y><![CDATA[%s]]></Location_Y>")
                .append("<Scale><![CDATA[%s]]></Scale>")
                .append("<Label><![CDATA[%s]]></Label>");

        return String.format(sb.toString(),locationX,locationY,scale,label);
    }
}

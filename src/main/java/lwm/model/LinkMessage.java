package lwm.model;

import lombok.Data;

import java.util.Map;

/**
 * 链接消息
 * Created by LWM on 2020/1/13.
 */
@Data
public class LinkMessage extends CommonMessage{

    private String title;

    private String description;

    private String url;

    @Override
    protected void initUnique(Map<String, String> requestMap) {
        title = requestMap.get("Title");
        description = requestMap.get("Description");
        url = requestMap.get("Url");
    }

    @Override
    public String getXmlContentString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<Title><![CDATA[%s]]></Title>")
                .append("<Description><![CDATA[%s]]></Description>")
                .append("<Url><![CDATA[%s]]></Url>");

        return String.format(sb.toString(),title,description,url);
    }
}

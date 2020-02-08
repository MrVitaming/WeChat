package lwm.model;

import lombok.Data;

import java.util.Map;

/**
 * Created by LWM on 2020/1/25.
 */
@Data
public class UnsubscribeMessage extends EventMessage{



    @Override
    protected void initUnique(Map<String, String> requestMap) {

    }

    @Override
    public String getXmlContentString() {
        return "";
    }
}

package lwm.model;

import lombok.Data;

import java.util.Map;

/**
 * 上报地理位置事件
 * Created by LWM on 2020/1/25.
 */
@Data
public class LocationEventMessage extends EventMessage{

    private String latitude;

    private String longitude;

    /**
     * 地址位置精度
     */
    private String precision;

    @Override
    protected void initUnique(Map<String, String> requestMap) {
        this.latitude = requestMap.get("Latitude");
        this.longitude = requestMap.get("Longitude");
        this.precision = requestMap.get("Precision");
    }

    @Override
    public String getXmlContentString() {
        return null;
    }
}

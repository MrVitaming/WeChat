package lwm.schedule;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import lwm.cache.LocalCache;
import lwm.util.WexinInterfaceUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by LWM on 2019/8/25.
 */
@Component
@Log4j2
public class TokenTask {

    @Value("${wechat.appid}")
    String appid;

    @Value("${wechat.secret}")
    String secret;

    @Scheduled(initialDelay = 5 * 1000l, fixedDelay = 2 * 3600 * 1000l)
    public void getAccessToken() {
        log.info("get token,start");

        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
        url = url + "&appid=" + appid + "&secret=" + secret;
        JSONObject jsonObject = WexinInterfaceUtil.httpRequest(url, "GET", null);
        String token = jsonObject.getString("access_token");
        log.info("access_token=" + token);

        LocalCache.ACCESS_TOKEN = token;

        log.info("get token,end");
    }

}

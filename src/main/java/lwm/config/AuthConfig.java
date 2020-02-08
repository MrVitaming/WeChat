package lwm.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 认证相关
 * Created by LWM on 2020/1/5.
 */
@Component
@Data
public class AuthConfig {

    @Value("${wechat.server.token}")
    private String token;

}

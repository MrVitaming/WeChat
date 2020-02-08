package lwm;

import lombok.extern.log4j.Log4j2;
import lwm.config.ThreadExecutorConfig;
import lwm.schedule.TokenTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

/**
 * Created by LWM on 2019/8/25.
 */
@Component
@Log4j2
public class MainThread implements CommandLineRunner {

    @Autowired
    private TokenTask weChatTask;

    @Autowired
    private ExecutorService executorService;

    @Override
    public void run(String... args) throws Exception {
        log.info("main thread start...");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("jvm shutdown...");
        }));

        log.info("main thread end...");
    }
}

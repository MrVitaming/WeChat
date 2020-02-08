package lwm.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync//开启异步调用
@Log4j2
public class ThreadExecutorConfig {

    /** 核心线程数 */
    private int corePoolSize = 10;

    /** 最大线程数 */
    private int maxPoolSize = 200;

    /** 队列数 */
    private int queueCapacity = 10;

    /**
     * @Configuration = <beans></beans>
     * @Bean = <bean></bean>
     * 返回值类型为<bean></bean>中的属性"class"对应的value
     * 方法名为<bean></bean>中的属性"id"对应的value
     * @return
     */
    @Bean
    public ExecutorService executorService(){
        log.info("executor init,start");

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("thread-pool");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());

        // 执行初始化
        executor.initialize();

        log.info("executor init,start");

        return executor.getThreadPoolExecutor();
    }

}
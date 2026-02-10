package cc.wdev.platform.commons.autoconfigure.core;

import cc.wdev.platform.commons.autoconfigure.core.properties.AsyncProperties;
import cc.wdev.platform.commons.core.async.AsyncTaskDecorator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author elvea
 */
@Slf4j
@EnableAsync
@AutoConfiguration
@ConditionalOnProperty(prefix = AsyncProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties({AsyncProperties.class})
public class AsyncAutoConfiguration implements AsyncConfigurer {

    private final AsyncProperties properties;

    public AsyncAutoConfiguration(AsyncProperties properties) {
        this.properties = properties;

        log.info("AsyncAutoConfiguration is enabled");
        log.info("AsyncAutoConfiguration corePoolSize - {}", properties.getCorePoolSize());
        log.info("AsyncAutoConfiguration maxPoolSize - {}", properties.getMaxPoolSize());
        log.info("AsyncAutoConfiguration keepAliveSeconds - {}", properties.getKeepAliveSeconds());
        log.info("AsyncAutoConfiguration queueCapacity - {}", properties.getQueueCapacity());
        log.info("AsyncAutoConfiguration threadNamePrefix - {}", properties.getThreadNamePrefix());
    }

    @Override
    public ThreadPoolTaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(properties.getCorePoolSize());
        executor.setMaxPoolSize(properties.getMaxPoolSize());
        executor.setKeepAliveSeconds(properties.getKeepAliveSeconds());
        executor.setQueueCapacity(properties.getQueueCapacity());
        executor.setThreadNamePrefix(properties.getThreadNamePrefix());
        executor.setTaskDecorator(new AsyncTaskDecorator());
        executor.initialize();
        return executor;
    }

}

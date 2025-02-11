package cc.elvea.platform.commons.autoconfigure.core;

import cc.elvea.platform.commons.autoconfigure.core.properties.StorageProperties;
import cc.elvea.platform.commons.core.storage.StorageConfig;
import cc.elvea.platform.commons.core.storage.StorageFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(StorageProperties.class)
@ConditionalOnProperty(prefix = StorageProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class StorageAutoConfiguration {

    public StorageAutoConfiguration(StorageProperties properties) {
        log.info("StorageAutoConfiguration is enabled.");
        log.info("Current Storage is {}", properties.getType());
        log.info("Min Storage is {}", properties.getMin().isEnabled());
        log.info("COS Storage is {}", properties.getCos().isEnabled());
        log.info("OSS Storage is {}", properties.getOss().isEnabled());
    }

    /**
     * @return {@link StorageFactory}
     */
    @Bean
    @ConditionalOnMissingBean
    StorageFactory storageManager(StorageProperties properties) {
        StorageConfig config = StorageConfig.builder()
                .type(properties.getType())
                .cos(properties.getCos())
                .oss(properties.getOss())
                .min(properties.getMin())
                .build();
        return new StorageFactory(config);
    }

}

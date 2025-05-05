package cc.elvea.platform.commons.autoconfigure.message;

import cc.elvea.platform.commons.autoconfigure.message.properties.BroadcastProperties;
import cc.elvea.platform.commons.message.broadcast.manager.DefaultBroadcastManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({BroadcastProperties.class})
@ConditionalOnProperty(prefix = BroadcastProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@Import({BroadcastRabbitAutoConfiguration.class})
public class BroadcastAutoConfiguration {

    public BroadcastAutoConfiguration() {
        log.info("BroadcastAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultBroadcastManager broadcastManager(BroadcastProperties properties) {
        return new DefaultBroadcastManager(properties.getType());
    }

}

package cc.wdev.platform.commons.autoconfigure.message;

import cc.wdev.platform.commons.autoconfigure.message.properties.RabbitProperties;
import cc.wdev.platform.commons.core.Context;
import cc.wdev.platform.commons.message.rabbit.RabbitUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author elvea
 */
@Slf4j
@AutoConfiguration
@ConditionalOnClass({RabbitTemplate.class})
@EnableConfigurationProperties({RabbitProperties.class})
@ConditionalOnProperty(prefix = RabbitProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class RabbitCustomAutoConfiguration {

    public RabbitCustomAutoConfiguration() {
        log.info("RabbitCustomAutoConfiguration is enabled");
    }

    @Bean
    @ConditionalOnMissingBean
    public RabbitUtils rabbitUtils(RabbitTemplate rabbitTemplate, Context context) {
        return new RabbitUtils(context, rabbitTemplate);
    }

}

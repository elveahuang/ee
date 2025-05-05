package cc.elvea.platform.commons.autoconfigure.message;

import cc.elvea.platform.commons.autoconfigure.message.properties.RabbitProperties;
import cc.elvea.platform.commons.core.Context;
import cc.elvea.platform.commons.message.rabbit.RabbitUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
@EnableConfigurationProperties({RabbitProperties.class})
@ConditionalOnProperty(prefix = RabbitProperties.PREFIX, name = "enabled", havingValue = "true")
public class RabbitCustomAutoConfiguration {

    public RabbitCustomAutoConfiguration() {
        log.info("RabbitCustomAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean
    public RabbitUtils amqpUtils(RabbitTemplate rabbitTemplate, Context context) {
        return new RabbitUtils(context, rabbitTemplate);
    }

}

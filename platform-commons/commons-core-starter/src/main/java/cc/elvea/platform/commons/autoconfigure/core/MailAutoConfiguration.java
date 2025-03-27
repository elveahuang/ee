package cc.elvea.platform.commons.autoconfigure.core;

import cc.elvea.platform.commons.autoconfigure.core.properties.MailProperties;
import cc.elvea.platform.commons.core.mail.MailFactory;
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
@EnableConfigurationProperties({MailProperties.class})
@ConditionalOnProperty(prefix = MailProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class MailAutoConfiguration {

    public MailAutoConfiguration(MailProperties properties) {
        log.info("MailAutoConfiguration is enabled.");
        log.info("MailAutoConfiguration server - {}.", properties.getConfig().getHost());
    }

    @Bean
    @ConditionalOnMissingBean
    public MailFactory mailFactory(MailProperties properties) {
        return new MailFactory(properties.getConfig());
    }

}

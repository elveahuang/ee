package cc.elvea.platform.commons.autoconfigure.extensions.mail;

import cc.elvea.platform.commons.autoconfigure.extensions.mail.properties.MailProperties;
import cc.elvea.platform.commons.extensions.mail.MailSender;
import cc.elvea.platform.commons.extensions.mail.spring.SpringMailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = MailProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties({MailProperties.class})
public class MailAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MailSender mailSender(MailProperties properties) {
        if (properties.getServer().isEnabled()) {
            return new SpringMailSender(properties.getServer());
        }
        return new SpringMailSender();
    }

}

package cn.elvea.platform.commons.core.autoconfigure.extensions.mail;

import cn.elvea.platform.commons.core.autoconfigure.extensions.mail.properties.MailProperties;
import cn.elvea.platform.commons.core.extensions.mail.MailSender;
import cn.elvea.platform.commons.core.extensions.mail.spring.SpringMailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = MailProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties({MailProperties.class})
public class MailAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MailSender mailSender(MailProperties properties) {
        if (properties.getServer().getEnabled()) {
            return new SpringMailSender(properties.getServer());
        }
        return new SpringMailSender();
    }

}

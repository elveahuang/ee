package top.baihu.platform.commons.autoconfigure.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.baihu.platform.commons.autoconfigure.core.properties.MailProperties;
import top.baihu.platform.commons.core.mail.MailFactory;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({MailProperties.class})
@ConditionalOnProperty(prefix = MailProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class MailAutoConfiguration {

    public MailAutoConfiguration(MailProperties properties) {
        log.info("MailAutoConfiguration is enabled");
        log.info("MailAutoConfiguration server host - {}", properties.getConfig().getHost());
        log.info("MailAutoConfiguration server port - {}", properties.getConfig().getPort());
    }

    @Bean
    @ConditionalOnMissingBean
    public MailFactory mailFactory(MailProperties properties) {
        return new MailFactory(properties.getConfig());
    }

}

package top.baihu.platform.commons.autoconfigure.extensions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.baihu.platform.commons.autoconfigure.extensions.properties.SensitiveProperties;
import top.baihu.platform.commons.extensions.sensitive.DefaultSensitiveService;
import top.baihu.platform.commons.extensions.sensitive.SensitiveService;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = SensitiveProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties({SensitiveProperties.class})
public class SensitiveAutoConfiguration {

    public SensitiveAutoConfiguration() {
        log.info("SensitiveAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean
    public SensitiveService sensitiveService() {
        return new DefaultSensitiveService();
    }

}

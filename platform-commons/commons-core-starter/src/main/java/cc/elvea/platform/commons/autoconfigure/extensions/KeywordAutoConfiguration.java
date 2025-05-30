package cc.elvea.platform.commons.autoconfigure.extensions;

import cc.elvea.platform.commons.autoconfigure.extensions.properties.KeywordProperties;
import cc.elvea.platform.commons.extensions.keyword.DefaultKeywordManager;
import cc.elvea.platform.commons.extensions.keyword.KeywordManager;
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
@EnableConfigurationProperties({KeywordProperties.class})
@ConditionalOnProperty(prefix = KeywordProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class KeywordAutoConfiguration {

    public KeywordAutoConfiguration() {
        log.info("KeywordAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean
    public KeywordManager keywordManager() {
        return new DefaultKeywordManager();
    }

}

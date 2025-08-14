package top.baihu.platform.commons.autoconfigure.extensions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.baihu.platform.commons.autoconfigure.extensions.properties.KeywordProperties;
import top.baihu.platform.commons.extensions.keyword.DefaultKeywordManager;
import top.baihu.platform.commons.extensions.keyword.KeywordManager;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({KeywordProperties.class})
@ConditionalOnProperty(prefix = KeywordProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class KeywordAutoConfiguration {

    public KeywordAutoConfiguration() {
        log.info("KeywordAutoConfiguration is enabled");
    }

    @Bean
    @ConditionalOnMissingBean
    public KeywordManager keywordManager() {
        return new DefaultKeywordManager();
    }

}

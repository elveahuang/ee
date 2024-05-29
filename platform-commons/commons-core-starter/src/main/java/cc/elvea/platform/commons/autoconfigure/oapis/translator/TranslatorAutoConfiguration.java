package cc.elvea.platform.commons.autoconfigure.oapis.translator;

import cc.elvea.platform.commons.autoconfigure.oapis.translator.properties.TranslatorProperties;
import cc.elvea.platform.commons.oapis.translator.TranslatorConfig;
import cc.elvea.platform.commons.oapis.translator.TranslatorManager;
import lombok.extern.slf4j.Slf4j;
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
@EnableConfigurationProperties({TranslatorProperties.class})
@ConditionalOnProperty(prefix = TranslatorProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class TranslatorAutoConfiguration {

    public TranslatorAutoConfiguration(TranslatorProperties properties) {
        log.info("Current default translator service is {}", properties.getType());
    }

    /**
     * @return {@link TranslatorManager}
     */
    @Bean
    public TranslatorManager translatorManager(TranslatorProperties properties) {
        TranslatorConfig config = TranslatorConfig.builder()
                .enabled(properties.isEnabled())
                .type(properties.getType())
                .aliyun(properties.getAliyun())
                .baidu(properties.getBaidu()).build();
        return new TranslatorManager(config);
    }

}

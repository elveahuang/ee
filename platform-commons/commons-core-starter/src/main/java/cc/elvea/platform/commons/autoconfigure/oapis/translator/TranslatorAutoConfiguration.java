package cc.elvea.platform.commons.autoconfigure.oapis.translator;

import cc.elvea.platform.commons.autoconfigure.oapis.translator.properties.TranslatorProperties;
import cc.elvea.platform.commons.oapis.translator.TranslatorConfig;
import cc.elvea.platform.commons.oapis.translator.TranslatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({TranslatorProperties.class})
@ConditionalOnProperty(prefix = TranslatorProperties.PREFIX, name = "enabled", havingValue = "true")
public class TranslatorAutoConfiguration {

    public TranslatorAutoConfiguration(TranslatorProperties properties) {
        log.info("TranslatorAutoConfiguration is enabled.");
        log.info("Current Translator type is {}", properties.getType());
    }

    @Bean
    public TranslatorFactory translatorFactory(TranslatorProperties properties) {
        TranslatorConfig config = TranslatorConfig.builder()
                .enabled(properties.isEnabled())
                .type(properties.getType())
                .aliyun(properties.getAliyun())
                .tencent(properties.getTencent()).build();
        return new TranslatorFactory(config);
    }

}

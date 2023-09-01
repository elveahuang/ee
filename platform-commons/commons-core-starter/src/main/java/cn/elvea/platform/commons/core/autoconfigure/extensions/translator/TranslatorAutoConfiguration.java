package cn.elvea.platform.commons.core.autoconfigure.extensions.translator;

import cn.elvea.platform.commons.core.autoconfigure.extensions.translator.properties.TranslatorProperties;
import cn.elvea.platform.commons.core.extensions.translator.TranslatorManager;
import cn.elvea.platform.commons.core.extensions.translator.TranslatorType;
import cn.elvea.platform.commons.core.extensions.translator.aliyun.AliyunTranslator;
import cn.elvea.platform.commons.core.extensions.translator.baidu.BaiduTranslator;
import lombok.extern.slf4j.Slf4j;
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
@EnableConfigurationProperties({TranslatorProperties.class})
@ConditionalOnProperty(prefix = TranslatorProperties.PREFIX, name = "enabled", havingValue = "true")
public class TranslatorAutoConfiguration {

    private final TranslatorProperties properties;

    public TranslatorAutoConfiguration(TranslatorProperties properties) {
        log.debug("current default translator is {}", properties.getType());
        this.properties = properties;
    }

    /**
     * @return {@link TranslatorManager}
     */
    @Bean
    public TranslatorManager translatorManager() {
        TranslatorManager translatorManager = new TranslatorManager(this.properties.getType());
        if (this.properties.getBaidu().getEnabled()) {
            translatorManager.addTranslator(TranslatorType.Baidu, new BaiduTranslator(properties.getBaidu()));
        }
        if (this.properties.getAliyun().getEnabled()) {
            translatorManager.addTranslator(TranslatorType.Aliyun, new AliyunTranslator(properties.getAliyun()));
        }
        return translatorManager;
    }

}

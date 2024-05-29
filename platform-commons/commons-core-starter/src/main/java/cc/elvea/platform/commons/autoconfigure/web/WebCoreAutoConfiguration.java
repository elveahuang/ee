package cc.elvea.platform.commons.autoconfigure.web;

import cc.elvea.platform.commons.autoconfigure.web.properties.WebProperties;
import cc.elvea.platform.commons.web.i18n.CustomLocaleResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore({WebMvcAutoConfiguration.class})
@EnableConfigurationProperties({WebProperties.class})
@ConditionalOnProperty(prefix = WebProperties.PREFIX, name = "enabled", havingValue = "true")
public class WebCoreAutoConfiguration {

    public WebCoreAutoConfiguration() {
        log.info("WebCoreAutoConfiguration is enabled.");
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new CustomLocaleResolver();
    }

}

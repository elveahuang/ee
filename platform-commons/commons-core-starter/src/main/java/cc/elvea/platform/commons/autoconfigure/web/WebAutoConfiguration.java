package cc.elvea.platform.commons.autoconfigure.web;

import cc.elvea.platform.commons.autoconfigure.web.properties.WebProperties;
import cc.elvea.platform.commons.utils.time.LegacyDateTimeAnnotationFormatterFactory;
import cc.elvea.platform.commons.utils.time.StandardDateTimeAnnotationFormatterFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({WebProperties.class})
@ConditionalOnProperty(prefix = WebProperties.PREFIX, name = "enabled", havingValue = "true")
public class WebAutoConfiguration implements WebMvcConfigurer {

    private LegacyDateTimeAnnotationFormatterFactory legacyDateTimeAnnotationFormatterFactory;

    private StandardDateTimeAnnotationFormatterFactory standardDateTimeAnnotationFormatterFactory;

    public WebAutoConfiguration() {
        log.info("WebAutoConfiguration is enabled.");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldAnnotation(legacyDateTimeAnnotationFormatterFactory);
        registry.addFormatterForFieldAnnotation(standardDateTimeAnnotationFormatterFactory);
    }

    @Autowired
    public void setLegacyDateTimeAnnotationFormatterFactory(LegacyDateTimeAnnotationFormatterFactory legacyDateTimeAnnotationFormatterFactory) {
        this.legacyDateTimeAnnotationFormatterFactory = legacyDateTimeAnnotationFormatterFactory;
    }

    @Autowired
    public void setStandardDateTimeAnnotationFormatterFactory(StandardDateTimeAnnotationFormatterFactory standardDateTimeAnnotationFormatterFactory) {
        this.standardDateTimeAnnotationFormatterFactory = standardDateTimeAnnotationFormatterFactory;
    }

}

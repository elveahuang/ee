package cc.elvea.platform.commons.autoconfigure.web;

import cc.elvea.platform.commons.autoconfigure.web.properties.WebProperties;
import cc.elvea.platform.commons.core.log.interceptor.UrlLogInterceptor;
import cc.elvea.platform.commons.utils.time.LegacyDateTimeAnnotationFormatterFactory;
import cc.elvea.platform.commons.utils.time.StandardDateTimeAnnotationFormatterFactory;
import cc.elvea.platform.commons.web.i18n.CustomLocaleResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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

    private final LegacyDateTimeAnnotationFormatterFactory legacyDateTimeAnnotationFormatterFactory;

    private final StandardDateTimeAnnotationFormatterFactory standardDateTimeAnnotationFormatterFactory;

    private final UrlLogInterceptor urlLogInterceptor;

    public WebAutoConfiguration(LegacyDateTimeAnnotationFormatterFactory legacyDateTimeAnnotationFormatterFactory, StandardDateTimeAnnotationFormatterFactory standardDateTimeAnnotationFormatterFactory, UrlLogInterceptor urlLogInterceptor) {
        log.info("WebAutoConfiguration is enabled.");
        this.legacyDateTimeAnnotationFormatterFactory = legacyDateTimeAnnotationFormatterFactory;
        this.standardDateTimeAnnotationFormatterFactory = standardDateTimeAnnotationFormatterFactory;
        this.urlLogInterceptor = urlLogInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.urlLogInterceptor);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldAnnotation(legacyDateTimeAnnotationFormatterFactory);
        registry.addFormatterForFieldAnnotation(standardDateTimeAnnotationFormatterFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public LocaleResolver localeResolver() {
        return new CustomLocaleResolver();
    }

    @Bean
    @ConditionalOnMissingBean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

}

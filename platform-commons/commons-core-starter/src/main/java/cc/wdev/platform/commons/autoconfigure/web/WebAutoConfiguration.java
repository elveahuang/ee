package cc.wdev.platform.commons.autoconfigure.web;

import cc.wdev.platform.commons.autoconfigure.web.properties.WebProperties;
import cc.wdev.platform.commons.core.log.interceptor.UrlLogInterceptor;
import cc.wdev.platform.commons.utils.JacksonUtils;
import cc.wdev.platform.commons.utils.i18n.CustomLocaleResolver;
import cc.wdev.platform.commons.utils.time.LegacyDateTimeAnnotationFormatterFactory;
import cc.wdev.platform.commons.utils.time.StandardDateTimeAnnotationFormatterFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClient;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({WebProperties.class})
@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = WebProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class WebAutoConfiguration implements WebMvcConfigurer {

    private final LegacyDateTimeAnnotationFormatterFactory legacyDateTimeAnnotationFormatterFactory;

    private final StandardDateTimeAnnotationFormatterFactory standardDateTimeAnnotationFormatterFactory;

    private final UrlLogInterceptor urlLogInterceptor;

    public WebAutoConfiguration(LegacyDateTimeAnnotationFormatterFactory legacyDateTimeAnnotationFormatterFactory,
                                StandardDateTimeAnnotationFormatterFactory standardDateTimeAnnotationFormatterFactory,
                                UrlLogInterceptor urlLogInterceptor) {
        log.info("WebAutoConfiguration is enabled");
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
    @Primary
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return new CorsFilter(source);
    }

    @Bean
    @ConditionalOnClass(RestClient.class)
    public RestClientCustomizer restClientCustomizer() {
        return builder -> builder.messageConverters(converters -> {
            converters.removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);
            converters.add(new MappingJackson2HttpMessageConverter(JacksonUtils.getObjectMapper()));
        });
    }

}

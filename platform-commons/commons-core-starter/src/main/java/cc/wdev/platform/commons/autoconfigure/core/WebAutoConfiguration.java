package cc.wdev.platform.commons.autoconfigure.core;

import cc.wdev.platform.commons.autoconfigure.core.properties.WebProperties;
import cc.wdev.platform.commons.utils.i18n.CustomLocaleResolver;
import cc.wdev.platform.commons.utils.time.LegacyDateTimeAnnotationFormatterFactory;
import cc.wdev.platform.commons.utils.time.StandardDateTimeAnnotationFormatterFactory;
import cc.wdev.platform.commons.web.converter.json.JacksonHttpMessageConverter;
import cc.wdev.platform.commons.web.filter.GlobalFilter;
import cc.wdev.platform.commons.web.interceptor.LogInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClient;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author elvea
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties({WebProperties.class})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnProperty(prefix = WebProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class WebAutoConfiguration implements WebMvcConfigurer {

    private final LegacyDateTimeAnnotationFormatterFactory legacyDateTimeAnnotationFormatterFactory;

    private final StandardDateTimeAnnotationFormatterFactory standardDateTimeAnnotationFormatterFactory;

    private final LogInterceptor logInterceptor;

    public WebAutoConfiguration(LegacyDateTimeAnnotationFormatterFactory legacyDateTimeAnnotationFormatterFactory,
                                StandardDateTimeAnnotationFormatterFactory standardDateTimeAnnotationFormatterFactory,
                                LogInterceptor logInterceptor) {
        log.info("WebAutoConfiguration is enabled");
        this.legacyDateTimeAnnotationFormatterFactory = legacyDateTimeAnnotationFormatterFactory;
        this.standardDateTimeAnnotationFormatterFactory = standardDateTimeAnnotationFormatterFactory;
        this.logInterceptor = logInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.logInterceptor);
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

    @Bean
    @Primary
    @ConditionalOnMissingBean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public CorsFilter corsFilter(CorsConfigurationSource source) {
        return new CorsFilter(source);
    }

    @Bean
    @ConditionalOnMissingBean
    public FilterRegistrationBean<GlobalFilter> globalFilterRegistration() {
        FilterRegistrationBean<GlobalFilter> registration = new FilterRegistrationBean<>(new GlobalFilter());
        registration.setEnabled(true);
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }

    @Bean
    @ConditionalOnClass(RestClient.class)
    public RestClientCustomizer restClientCustomizer() {
        return builder -> builder.messageConverters(converters -> {
            converters.removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);
            converters.add(new JacksonHttpMessageConverter());
        });
    }

}

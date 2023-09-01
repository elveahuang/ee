package cn.elvea.platform.commons.core.autoconfigure.core;

import cn.elvea.platform.commons.core.autoconfigure.core.properties.CommonsProperties;
import cn.elvea.platform.commons.core.autoconfigure.extensions.time.properties.DateTimeProperties;
import cn.elvea.platform.commons.core.autoconfigure.log.properties.LogProperties;
import cn.elvea.platform.commons.core.context.Context;
import cn.elvea.platform.commons.core.extensions.i18n.DefaultLanguageResolver;
import cn.elvea.platform.commons.core.extensions.i18n.LanguageResolver;
import cn.elvea.platform.commons.core.extensions.json.jackson.CustomJsonModule;
import cn.elvea.platform.commons.core.extensions.time.DefaultTimeZoneResolver;
import cn.elvea.platform.commons.core.extensions.time.LegacyDateTimeAnnotationFormatterFactory;
import cn.elvea.platform.commons.core.extensions.time.StandardDateTimeAnnotationFormatterFactory;
import cn.elvea.platform.commons.core.extensions.time.TimeZoneResolver;
import cn.elvea.platform.commons.core.utils.SpringUtils;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({
        CommonsProperties.class,
        DateTimeProperties.class,
        LogProperties.class,
})
public class CommonsAutoConfiguration {

    public CommonsAutoConfiguration() {
        log.info("CommonsAutoConfiguration is enabled.");
    }

    /**
     * @return {@link Context}
     */
    @Bean(name = "context")
    @ConditionalOnMissingBean
    public Context Context(CommonsProperties properties) {
        return Context.builder()
                .debugEnabled(properties.getDebug().getEnabled())
                .amqpEnabled(properties.getAmqp().getEnabled())
                .build();
    }

    /**
     * @return {@link SpringUtils}
     */
    @Bean
    @ConditionalOnMissingBean
    public SpringUtils springUtils() {
        return new SpringUtils();
    }

    /**
     * @return {@link TimeZoneResolver}
     */
    @Bean
    @ConditionalOnMissingBean
    public TimeZoneResolver timeZoneResolver(CommonsProperties properties) {
        return new DefaultTimeZoneResolver(properties.getUserZoneId(), properties.getSystemZoneId());
    }

    /**
     * @return {@link LegacyDateTimeAnnotationFormatterFactory}
     */
    @Bean
    @ConditionalOnMissingBean
    public LegacyDateTimeAnnotationFormatterFactory legacyDateTimeAnnotationFormatterFactory(TimeZoneResolver timeZoneResolver) {
        return new LegacyDateTimeAnnotationFormatterFactory(timeZoneResolver);
    }

    /**
     * @return {@link StandardDateTimeAnnotationFormatterFactory}
     */
    @Bean
    @ConditionalOnMissingBean
    public StandardDateTimeAnnotationFormatterFactory standardDateTimeAnnotationFormatterFactory(TimeZoneResolver timeZoneResolver) {
        return new StandardDateTimeAnnotationFormatterFactory(timeZoneResolver);
    }

    /**
     * @return {@link LanguageResolver}
     */
    @Bean
    @ConditionalOnMissingBean
    public LanguageResolver languageResolver(CommonsProperties properties) {
        return new DefaultLanguageResolver(properties.getLanguage());
    }

    /**
     * Jackson Customizer
     */
    @Bean
    public CustomJsonModule customJsonModule(TimeZoneResolver resolver) {
        return new CustomJsonModule(resolver);
    }

    /**
     * Jackson Customizer
     */
    @Bean
    public CustomObjectMapperBuilderCustomizer objectMapperBuilderCustomizer(CustomJsonModule customJsonModule) {
        return new CustomObjectMapperBuilderCustomizer(customJsonModule);
    }

    /**
     * CustomObjectMapperBuilderCustomizer
     */
    public static class CustomObjectMapperBuilderCustomizer implements Jackson2ObjectMapperBuilderCustomizer, Ordered {

        private final CustomJsonModule customJsonModule;

        public CustomObjectMapperBuilderCustomizer(CustomJsonModule customJsonModule) {
            this.customJsonModule = customJsonModule;
        }

        @Override
        public void customize(Jackson2ObjectMapperBuilder builder) {
            builder.modulesToInstall(new JavaTimeModule());
            builder.modulesToInstall(new Jdk8Module());
            builder.modulesToInstall(this.customJsonModule);
        }

        @Override
        public int getOrder() {
            return 1;
        }

    }

}

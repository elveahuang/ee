package cc.elvea.platform.commons.autoconfigure.core;

import cc.elvea.platform.commons.autoconfigure.core.properties.CoreProperties;
import cc.elvea.platform.commons.context.Context;
import cc.elvea.platform.commons.extensions.i18n.DefaultLanguageResolver;
import cc.elvea.platform.commons.extensions.i18n.LanguageResolver;
import cc.elvea.platform.commons.utils.SpringUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.commons.utils.jackson.CustomJsonModule;
import cc.elvea.platform.commons.utils.time.DefaultTimeZoneResolver;
import cc.elvea.platform.commons.utils.time.LegacyDateTimeAnnotationFormatterFactory;
import cc.elvea.platform.commons.utils.time.StandardDateTimeAnnotationFormatterFactory;
import cc.elvea.platform.commons.utils.time.TimeZoneResolver;
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
 * @since 24.1.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({CoreProperties.class})
public class CoreAutoConfiguration {

    public CoreAutoConfiguration() {
        log.info("CommonsAutoConfiguration is enabled.");
    }

    /**
     * @return {@link Context}
     */
    @Bean(name = "context")
    @ConditionalOnMissingBean
    public Context Context(CoreProperties properties) {
        Context.Home home = Context.Home.builder()
                .admin(StringUtils.isNotEmpty(properties.getHome().getAdmin()) ? properties.getHome().getAdmin() : "")
                .webapp(StringUtils.isNotEmpty(properties.getHome().getWebapp()) ? properties.getHome().getWebapp() : "")
                .mobile(StringUtils.isNotEmpty(properties.getHome().getMobile()) ? properties.getHome().getMobile() : "")
                .main(StringUtils.isNotEmpty(properties.getHome().getMain()) ? properties.getHome().getMain() : "")
                .build();

        return Context.builder()
                .debugEnabled(properties.getDebug().isEnabled())
                .amqpEnabled(properties.getAmqp().isEnabled())
                .home(home)
                .mode(properties.getMode())
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
    public TimeZoneResolver timeZoneResolver(CoreProperties properties) {
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
    public LanguageResolver languageResolver(CoreProperties properties) {
        return new DefaultLanguageResolver(properties.getLanguage());
    }

    /**
     * Jackson Customizer
     */
    @Bean
    public CustomObjectMapperBuilderCustomizer objectMapperBuilderCustomizer() {
        return new CustomObjectMapperBuilderCustomizer();
    }

    /**
     * CustomObjectMapperBuilderCustomizer
     */
    public static class CustomObjectMapperBuilderCustomizer implements Jackson2ObjectMapperBuilderCustomizer, Ordered {

        @Override
        public void customize(Jackson2ObjectMapperBuilder builder) {
            builder.modulesToInstall(new JavaTimeModule());
            builder.modulesToInstall(new Jdk8Module());
            builder.modulesToInstall(new CustomJsonModule());
        }

        @Override
        public int getOrder() {
            return 1;
        }

    }

}

package cc.elvea.platform.commons.autoconfigure.core;

import cc.elvea.platform.commons.autoconfigure.core.properties.CoreProperties;
import cc.elvea.platform.commons.constants.GlobalConstants;
import cc.elvea.platform.commons.core.Context;
import cc.elvea.platform.commons.utils.SpringUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.commons.utils.i18n.DefaultLanguageResolver;
import cc.elvea.platform.commons.utils.i18n.LanguageResolver;
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
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({CoreProperties.class})
public class CoreAutoConfiguration {

    public CoreAutoConfiguration(CoreProperties properties) {
        log.info("CoreAutoConfiguration is enabled.");

        if (properties.getDebug().isEnabled()) {
            log.info("Debug is enabled.");
        } else {
            log.info("Debug is disabled.");
        }

        if (properties.getAmqp().isEnabled()) {
            log.info("RabbitMQ is enabled.");
        } else {
            log.info("RabbitMQ is disabled.");
        }
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringUtils springUtils() {
        return new SpringUtils();
    }

    @Bean(name = "context")
    @ConditionalOnMissingBean
    public Context Context(CoreProperties properties) {
        Context.Home home = Context.Home.builder()
                .admin(StringUtils.isNotEmpty(properties.getHome().getAdmin()) ? properties.getHome().getAdmin() : "")
                .webapp(StringUtils.isNotEmpty(properties.getHome().getWebapp()) ? properties.getHome().getWebapp() : "")
                .mobile(StringUtils.isNotEmpty(properties.getHome().getMobile()) ? properties.getHome().getMobile() : "")
                .main(StringUtils.isNotEmpty(properties.getHome().getMain()) ? properties.getHome().getMain() : "")
                .build();

        String applicationTitle = StringUtils.isEmpty(properties.getApplicationName()) ?
                GlobalConstants.NAME : properties.getApplicationName();

        String applicationVersion = StringUtils.isEmpty(properties.getApplicationVersion()) ?
                GlobalConstants.VERSION : properties.getApplicationVersion();

        return Context.builder()
                .applicationName(applicationTitle)
                .applicationVersion(applicationVersion)
                .debugEnabled(properties.getDebug().isEnabled())
                .amqpEnabled(properties.getAmqp().isEnabled())
                .home(home)
                .build();
    }

    // ------------------------------------------------------------------------------------------------------------------------
    // 时区和日期
    // ------------------------------------------------------------------------------------------------------------------------

    @Bean
    @ConditionalOnMissingBean
    public TimeZoneResolver timeZoneResolver(CoreProperties properties) {
        return new DefaultTimeZoneResolver(properties.getUserZoneId(), properties.getSystemZoneId());
    }

    @Bean
    @ConditionalOnMissingBean
    public LegacyDateTimeAnnotationFormatterFactory legacyDateTimeAnnotationFormatterFactory(TimeZoneResolver timeZoneResolver) {
        return new LegacyDateTimeAnnotationFormatterFactory(timeZoneResolver);
    }

    @Bean
    @ConditionalOnMissingBean
    public StandardDateTimeAnnotationFormatterFactory standardDateTimeAnnotationFormatterFactory(TimeZoneResolver timeZoneResolver) {
        return new StandardDateTimeAnnotationFormatterFactory(timeZoneResolver);
    }

    @Bean
    @ConditionalOnMissingBean
    public LanguageResolver languageResolver(CoreProperties properties) {
        return new DefaultLanguageResolver(properties.getLanguage());
    }

    // ------------------------------------------------------------------------------------------------------------------------
    // Jackson
    // ------------------------------------------------------------------------------------------------------------------------

    @Bean
    public CustomObjectMapperBuilderCustomizer objectMapperBuilderCustomizer() {
        return new CustomObjectMapperBuilderCustomizer();
    }

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

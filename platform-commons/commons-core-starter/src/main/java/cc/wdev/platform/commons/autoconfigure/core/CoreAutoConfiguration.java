package cc.wdev.platform.commons.autoconfigure.core;

import cc.wdev.platform.commons.autoconfigure.core.properties.AsyncProperties;
import cc.wdev.platform.commons.autoconfigure.core.properties.CoreProperties;
import cc.wdev.platform.commons.autoconfigure.core.properties.WebProperties;
import cc.wdev.platform.commons.core.Context;
import cc.wdev.platform.commons.core.tenant.DefaultTenantResolver;
import cc.wdev.platform.commons.core.tenant.GlobalTenantManager;
import cc.wdev.platform.commons.core.tenant.TenantStore;
import cc.wdev.platform.commons.utils.EnvironmentUtils;
import cc.wdev.platform.commons.utils.MessageSourceUtils;
import cc.wdev.platform.commons.utils.SpringUtils;
import cc.wdev.platform.commons.utils.i18n.DefaultLanguageResolver;
import cc.wdev.platform.commons.utils.i18n.LanguageResolver;
import cc.wdev.platform.commons.utils.jackson.CustomJsonModule;
import cc.wdev.platform.commons.utils.time.DefaultTimeZoneResolver;
import cc.wdev.platform.commons.utils.time.LegacyDateTimeAnnotationFormatterFactory;
import cc.wdev.platform.commons.utils.time.StandardDateTimeAnnotationFormatterFactory;
import cc.wdev.platform.commons.utils.time.TimeZoneResolver;
import cc.wdev.platform.commons.web.interceptor.TraceInterceptor;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author elvea
 */
@Slf4j
@EnableConfigurationProperties({CoreProperties.class, AsyncProperties.class})
public class CoreAutoConfiguration {

    private final CoreProperties properties;

    public CoreAutoConfiguration(CoreProperties properties) {
        log.info("CoreAutoConfiguration is enabled");
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringUtils springUtils() {
        return new SpringUtils();
    }

    @Bean
    @ConditionalOnMissingBean
    public EnvironmentUtils environmentUtils() {
        return new EnvironmentUtils();
    }

    @Bean
    @ConditionalOnMissingBean
    public MessageSourceUtils messageSourceUtils() {
        return new MessageSourceUtils();
    }

    @Bean(name = "context")
    @ConditionalOnMissingBean
    public Context Context() {
        log.info("Context init...");
        log.info("Context Debug {}", this.properties.getDebug().isEnabled() ? "enabled" : "disabled");
        log.info("Context Rabbit {}", this.properties.getRabbit().isEnabled() ? "enabled" : "disabled");
        log.info("Context Tenancy {}", this.properties.getTenancy().isEnabled() ? "enabled" : "disabled");

        GlobalTenantManager.init(this.properties.getTenancy());

        return Context.builder()
            .app(properties.getApp())
            .debug(properties.getDebug())
            .home(properties.getHome())
            .rabbit(properties.getRabbit())
            .tenancy(properties.getTenancy())
            .build();
    }

    // ------------------------------------------------------------------------------------------------------------------------
    // 日志跟踪
    // ------------------------------------------------------------------------------------------------------------------------

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = WebProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
    public TraceInterceptor traceInterceptor() {
        log.info("Creating TraceInterceptor");
        return new TraceInterceptor();
    }

    // ------------------------------------------------------------------------------------------------------------------------
    // 时区和日期
    // ------------------------------------------------------------------------------------------------------------------------

    @Bean
    @ConditionalOnMissingBean
    public TimeZoneResolver timeZoneResolver(Context context) {
        return new DefaultTimeZoneResolver(context.getApp().getUserZoneId(), context.getApp().getSystemZoneId());
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
    public LanguageResolver languageResolver(Context context) {
        return new DefaultLanguageResolver(context.getApp().getLanguage());
    }

    // ------------------------------------------------------------------------------------------------------------------------
    // Multi Tenancy
    // ------------------------------------------------------------------------------------------------------------------------

    @Bean
    @ConditionalOnMissingBean
    public TenantStore tenantStore() {
        return new TenantStore() {
        };
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultTenantResolver tenantResolver(TenantStore tenantStore) {
        return new DefaultTenantResolver(tenantStore);
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

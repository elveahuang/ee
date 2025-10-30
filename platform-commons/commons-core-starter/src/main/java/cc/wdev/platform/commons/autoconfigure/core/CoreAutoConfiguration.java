package cc.wdev.platform.commons.autoconfigure.core;

import cc.wdev.platform.commons.autoconfigure.core.properties.AsyncProperties;
import cc.wdev.platform.commons.autoconfigure.core.properties.CoreProperties;
import cc.wdev.platform.commons.core.Context;
import cc.wdev.platform.commons.core.tenant.DefaultTenantStore;
import cc.wdev.platform.commons.core.tenant.GlobalTenantManager;
import cc.wdev.platform.commons.core.tenant.TenantConfig;
import cc.wdev.platform.commons.core.tenant.TenantStore;
import cc.wdev.platform.commons.utils.EnvironmentUtils;
import cc.wdev.platform.commons.utils.MapStructUtils;
import cc.wdev.platform.commons.utils.MessageSourceUtils;
import cc.wdev.platform.commons.utils.SpringUtils;
import cc.wdev.platform.commons.utils.i18n.DefaultLanguageResolver;
import cc.wdev.platform.commons.utils.i18n.LanguageResolver;
import cc.wdev.platform.commons.utils.jackson.CustomJsonModule;
import cc.wdev.platform.commons.utils.time.DefaultTimeZoneResolver;
import cc.wdev.platform.commons.utils.time.LegacyDateTimeAnnotationFormatterFactory;
import cc.wdev.platform.commons.utils.time.StandardDateTimeAnnotationFormatterFactory;
import cc.wdev.platform.commons.utils.time.TimeZoneResolver;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
    public Context Context(TenantStore tenantStore, TenantConfig tenantConfig) {
        log.info("Context init...");
        log.info("Context Debug {}", this.properties.getDebug().isEnabled() ? "enabled" : "disabled");
        log.info("Context Rabbit {}", this.properties.getRabbit().isEnabled() ? "enabled" : "disabled");
        log.info("Context Tenancy {}", this.properties.getTenancy().isEnabled() ? "enabled" : "disabled");

        // 初始化多租户管理器
        GlobalTenantManager.init(tenantStore, tenantConfig);

        // 初始化转换器模式
        MapStructUtils.init(properties.getMapStruct().getComponentModel());

        return Context.builder()
            .app(properties.getApp())
            .debug(properties.getDebug())
            .home(properties.getHome())
            .rabbit(properties.getRabbit())
            .tenancy(properties.getTenancy())
            .mapStruct(properties.getMapStruct())
            .build();
    }

    // ------------------------------------------------------------------------------------------------------------------------
    // Multi Tenancy
    // ------------------------------------------------------------------------------------------------------------------------

    @Bean
    @ConditionalOnMissingBean
    public TenantStore tenantStore() {
        return new DefaultTenantStore();
    }

    @Bean
    @ConditionalOnMissingBean
    public TenantConfig tenantConfig() {
        return this.properties.getTenancy();
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

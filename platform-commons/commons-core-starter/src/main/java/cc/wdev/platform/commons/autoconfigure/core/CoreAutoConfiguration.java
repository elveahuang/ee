package cc.wdev.platform.commons.autoconfigure.core;

import cc.wdev.platform.commons.autoconfigure.core.properties.AsyncProperties;
import cc.wdev.platform.commons.autoconfigure.core.properties.CoreProperties;
import cc.wdev.platform.commons.core.Context;
import cc.wdev.platform.commons.core.tenant.DefaultTenantStore;
import cc.wdev.platform.commons.core.tenant.GlobalTenantManager;
import cc.wdev.platform.commons.core.tenant.TenantConfig;
import cc.wdev.platform.commons.core.tenant.TenantStore;
import cc.wdev.platform.commons.utils.*;
import cc.wdev.platform.commons.utils.i18n.DefaultLanguageResolver;
import cc.wdev.platform.commons.utils.i18n.LanguageResolver;
import cc.wdev.platform.commons.utils.time.DefaultTimeZoneResolver;
import cc.wdev.platform.commons.utils.time.LegacyDateTimeAnnotationFormatterFactory;
import cc.wdev.platform.commons.utils.time.StandardDateTimeAnnotationFormatterFactory;
import cc.wdev.platform.commons.utils.time.TimeZoneResolver;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import tools.jackson.databind.SerializationFeature;

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

    @Bean
    @ConditionalOnMissingBean
    public ValidationUtils validationUtils(Validator validator) {
        return new ValidationUtils(validator);
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
    // Security
    // ------------------------------------------------------------------------------------------------------------------------

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass({PasswordEncoder.class})
    public PasswordEncoder passwordEncoder() {
        return SecurityUtils.getPasswordEncoder();
    }

    // ------------------------------------------------------------------------------------------------------------------------
    // Jackson
    // ------------------------------------------------------------------------------------------------------------------------

    @Bean
    public JsonMapperBuilderCustomizer jsonMapperBuilderCustomizer() {
        return builder -> {
            builder.enable(SerializationFeature.INDENT_OUTPUT);
            builder.changeDefaultPropertyInclusion(v -> v.withValueInclusion(JsonInclude.Include.NON_NULL));
            builder.changeDefaultPropertyInclusion(v -> v.withContentInclusion(JsonInclude.Include.NON_NULL));
        };
    }

}

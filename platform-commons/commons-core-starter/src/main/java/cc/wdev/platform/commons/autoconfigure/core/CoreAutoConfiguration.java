package cc.wdev.platform.commons.autoconfigure.core;

import cc.wdev.platform.commons.autoconfigure.core.properties.AsyncProperties;
import cc.wdev.platform.commons.autoconfigure.core.properties.CoreProperties;
import cc.wdev.platform.commons.autoconfigure.core.properties.WebProperties;
import cc.wdev.platform.commons.constants.GlobalConstants;
import cc.wdev.platform.commons.core.Context;
import cc.wdev.platform.commons.core.tenant.DefaultTenantResolver;
import cc.wdev.platform.commons.core.tenant.GlobalTenantManager;
import cc.wdev.platform.commons.core.tenant.TenantConfig;
import cc.wdev.platform.commons.core.tenant.TenantStore;
import cc.wdev.platform.commons.utils.SpringUtils;
import cc.wdev.platform.commons.utils.StringUtils;
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
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.lang.NonNull;

/**
 * @author elvea
 */
@Slf4j
@EnableConfigurationProperties({CoreProperties.class, AsyncProperties.class})
public class CoreAutoConfiguration implements ApplicationContextAware {

    private final CoreProperties properties;

    public CoreAutoConfiguration(CoreProperties properties) {
        log.info("CoreAutoConfiguration is enabled");
        log.info("CoreAutoConfiguration debug {}", properties.getDebug().isEnabled() ? "enabled" : "disabled");
        log.info("CoreAutoConfiguration amqp {}", properties.getAmqp().isEnabled() ? "enabled" : "disabled");
        log.info("CoreAutoConfiguration multi-tenancy {}", properties.getMultiTenancy().isEnabled() ? "enabled" : "disabled");

        this.properties = properties;
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        log.info("CoreAutoConfiguration GlobalTenantManager initialize ...");
        TenantConfig config = TenantConfig.builder().enabled(properties.getMultiTenancy().isEnabled()).build();
        GlobalTenantManager.setConfig(config);
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

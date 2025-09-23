package cc.wdev.platform.commons.autoconfigure.data;

import cc.wdev.platform.commons.autoconfigure.core.CoreAutoConfiguration;
import cc.wdev.platform.commons.autoconfigure.core.properties.CoreProperties;
import cc.wdev.platform.commons.autoconfigure.data.properties.JpaCustomProperties;
import cc.wdev.platform.commons.core.tenant.GlobalTenantManager;
import cc.wdev.platform.commons.core.tenant.TenantConfig;
import cc.wdev.platform.commons.data.jpa.id.CustomIdentifierGenerator;
import cc.wdev.platform.commons.data.jpa.repository.BaseEntityRepositoryImpl;
import cc.wdev.platform.commons.data.jpa.tenant.TenantIdResolver;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.lang.NonNull;

/**
 * @author elvea
 */
@Slf4j
@EnableConfigurationProperties(JpaCustomProperties.class)
@AutoConfigureAfter({CoreAutoConfiguration.class})
@ConditionalOnClass({HibernateJpaAutoConfiguration.class})
@ConditionalOnProperty(prefix = JpaCustomProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableJpaAuditing
@EnableJpaRepositories(repositoryBaseClass = BaseEntityRepositoryImpl.class)
public class JpaCustomAutoConfiguration implements ApplicationContextAware {

    private final JpaCustomProperties properties;

    private final CoreProperties coreProperties;

    public JpaCustomAutoConfiguration(JpaCustomProperties properties, CoreProperties coreProperties) {
        log.info("JpaCustomAutoConfiguration is enabled");
        log.info("JpaCustomAutoConfiguration multi-tenancy {}", coreProperties.getMultiTenancy().isEnabled() ? "enabled" : "disabled");

        this.properties = properties;
        this.coreProperties = coreProperties;
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        log.info("CoreAutoConfiguration GlobalTenantManager initialize ...");
        TenantConfig config = TenantConfig.builder().enabled(coreProperties.getMultiTenancy().isEnabled()).build();
        GlobalTenantManager.setConfig(config);
    }

    @Bean("jpaIdentifierGenerator")
    @ConditionalOnMissingBean
    public IdentifierGenerator jpaIdentifierGenerator() {
        return new CustomIdentifierGenerator();
    }

    @Bean("jpaTenantIdentifierResolver")
    @ConditionalOnMissingBean
    public CurrentTenantIdentifierResolver<Long> jpaTenantIdentifierResolver() {
        return new TenantIdResolver(this.coreProperties.getMultiTenancy().isEnabled());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(@Autowired(required = false) CurrentTenantIdentifierResolver<Long> jpaTenantIdentifierResolver) {
        return properties -> {
            properties.put("hibernate.show_sql", this.properties.isShowSql());
            properties.put("hibernate.format_sql", this.properties.isFormatSql());

            // 开启多租户解析器
            if (this.coreProperties.getMultiTenancy().isEnabled()) {
                properties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, jpaTenantIdentifierResolver);
            }
        };
    }

}

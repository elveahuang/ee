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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
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
@AutoConfiguration(after = {CoreAutoConfiguration.class})
@ConditionalOnClass({HibernateJpaAutoConfiguration.class})
@ConditionalOnProperty(prefix = JpaCustomProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties(JpaCustomProperties.class)
@EnableJpaAuditing
@EnableJpaRepositories(repositoryBaseClass = BaseEntityRepositoryImpl.class)
public class JpaCustomAutoConfiguration implements ApplicationContextAware {

    private final CoreProperties coreProperties;

    private final JpaCustomProperties properties;

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
    @ConditionalOnProperty(prefix = CoreProperties.TENANCY_PREFIX, name = "enabled", havingValue = "true")
    public CurrentTenantIdentifierResolver<Long> jpaTenantIdentifierResolver() {
        return new TenantIdResolver();
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(
        @Qualifier("jpaTenantIdentifierResolver") @Autowired(required = false) CurrentTenantIdentifierResolver<Long> jpaTenantIdentifierResolver
    ) {
        log.info("Create Hibernate Configuration Customizer");
        log.info("Hibernate showSql - {}", this.properties.isShowSql());
        log.info("Hibernate formatSql - {}", this.properties.isFormatSql());

        return properties -> {
            properties.put("hibernate.show_sql", this.properties.isShowSql());
            properties.put("hibernate.format_sql", this.properties.isFormatSql());

            // 开启多租户解析器
            if (this.coreProperties.getMultiTenancy().isEnabled() && jpaTenantIdentifierResolver == null) {
                log.info("Multi-tenancy is enabled, but jpaTenantIdentifierResolver is null.");
            } else if (this.coreProperties.getMultiTenancy().isEnabled()) {
                log.info("Multi-tenancy is enabled, init CurrentTenantIdentifierResolver.");
                properties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, jpaTenantIdentifierResolver);
            }
        };
    }

}

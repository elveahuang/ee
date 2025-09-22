package cc.wdev.platform.commons.autoconfigure.data;

import cc.wdev.platform.commons.autoconfigure.core.properties.TenantProperties;
import cc.wdev.platform.commons.autoconfigure.data.properties.JpaCustomProperties;
import cc.wdev.platform.commons.data.jpa.id.CustomIdentifierGenerator;
import cc.wdev.platform.commons.data.jpa.repository.BaseEntityRepositoryImpl;
import cc.wdev.platform.commons.data.jpa.tenancy.CustomTenantIdentifierResolver;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(JpaCustomProperties.class)
@ConditionalOnClass({HibernateJpaAutoConfiguration.class})
@ConditionalOnProperty(prefix = JpaCustomProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableJpaAuditing
@EnableJpaRepositories(repositoryBaseClass = BaseEntityRepositoryImpl.class)
public class JpaCustomAutoConfiguration {

    private final JpaCustomProperties properties;

    private final TenantProperties tenantProperties;

    public JpaCustomAutoConfiguration(JpaCustomProperties properties, TenantProperties tenantProperties) {
        log.info("JpaCustomAutoConfiguration is enabled");
        this.properties = properties;
        this.tenantProperties = tenantProperties;
    }

    @Bean("jpaIdentifierGenerator")
    @ConditionalOnMissingBean
    public IdentifierGenerator jpaIdentifierGenerator() {
        return new CustomIdentifierGenerator();
    }

    @Bean("jpaTenantIdentifierResolver")
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = TenantProperties.PREFIX, name = "enabled", havingValue = "true")
    public CurrentTenantIdentifierResolver<Long> jpaTenantIdentifierResolver() {
        return new CustomTenantIdentifierResolver();
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(@Autowired(required = false) CurrentTenantIdentifierResolver<Long> jpaTenantIdentifierResolver) {
        return hibernateProperties -> {
            hibernateProperties.put("hibernate.show_sql", this.properties.isShowSql());
            hibernateProperties.put("hibernate.format_sql", this.properties.isFormatSql());

            // 开启多租户解析器
            if (this.tenantProperties != null && this.tenantProperties.isEnabled()) {
                hibernateProperties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, jpaTenantIdentifierResolver);
            }
        };
    }

}

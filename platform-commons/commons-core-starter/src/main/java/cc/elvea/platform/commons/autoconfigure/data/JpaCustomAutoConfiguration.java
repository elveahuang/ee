package cc.elvea.platform.commons.autoconfigure.data;

import cc.elvea.platform.commons.autoconfigure.data.properties.JpaCustomProperties;
import cc.elvea.platform.commons.data.jpa.id.CustomIdentifierGenerator;
import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.id.IdentifierGenerator;
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

    public JpaCustomAutoConfiguration() {
        log.info("JpaCustomAutoConfiguration is enabled.");
    }

    @Bean("jpaIdentifierGenerator")
    @ConditionalOnMissingBean
    public IdentifierGenerator jpaIdentifierGenerator() {
        return new CustomIdentifierGenerator();
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(JpaCustomProperties properties) {
        return hibernateProperties -> {
            hibernateProperties.put("hibernate.show_sql", properties.isShowSql());
            hibernateProperties.put("hibernate.format_sql", properties.isFormatSql());
        };
    }

}

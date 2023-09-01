package cn.elvea.platform.commons.core.autoconfigure.data.jpa;

import cn.elvea.platform.commons.core.autoconfigure.data.jpa.properties.CustomJpaProperties;
import cn.elvea.platform.commons.core.data.jpa.id.CustomIdentifierGenerator;
import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepositoryImpl;
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
 * @since 0.0.1
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({HibernateJpaAutoConfiguration.class})
@ConditionalOnProperty(prefix = CustomJpaProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableJpaRepositories(repositoryBaseClass = BaseEntityRepositoryImpl.class)
@EnableJpaAuditing
@EnableConfigurationProperties(CustomJpaProperties.class)
public class CustomJpaAutoConfiguration {

    public CustomJpaAutoConfiguration() {
        log.info("CustomJpaAutoConfiguration is enabled.");
    }

    /**
     * @see IdentifierGenerator
     */
    @Bean("jpaIdentifierGenerator")
    @ConditionalOnMissingBean
    public IdentifierGenerator identifierGenerator() {
        return new CustomIdentifierGenerator();
    }

    /**
     * @see HibernatePropertiesCustomizer
     */
    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(CustomJpaProperties properties) {
        return hibernateProperties -> hibernateProperties.put("hibernate.show_sql", properties.getShowSql());
    }

}

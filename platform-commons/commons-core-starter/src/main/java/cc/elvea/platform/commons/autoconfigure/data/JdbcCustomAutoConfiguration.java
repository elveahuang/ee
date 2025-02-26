package cc.elvea.platform.commons.autoconfigure.data;

import cc.elvea.platform.commons.autoconfigure.data.properties.JdbcCustomProperties;
import cc.elvea.platform.commons.data.jdbc.dao.Dao;
import cc.elvea.platform.commons.data.jdbc.dialect.DbDialect;
import cc.elvea.platform.commons.data.jdbc.utils.JdbcUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(JdbcCustomProperties.class)
@ConditionalOnClass({JdbcTemplate.class})
@ConditionalOnProperty(prefix = JdbcCustomProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class JdbcCustomAutoConfiguration {

    public JdbcCustomAutoConfiguration() {
        log.info("JdbcCustomAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean
    public DbDialect dbDialect(JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.execute(JdbcUtils::getDialect);
    }

    @Bean
    @ConditionalOnMissingBean
    public Dao dao(JdbcTemplate jdbcTemplate) {
        return new Dao(jdbcTemplate);
    }

}

package cc.wdev.platform.commons.autoconfigure.data;

import cc.wdev.platform.commons.autoconfigure.data.properties.DataCoreProperties;
import cc.wdev.platform.commons.autoconfigure.data.properties.DataJdbcProperties;
import cc.wdev.platform.commons.data.core.auditor.UserAuditorAware;
import cc.wdev.platform.commons.data.jdbc.dao.Dao;
import cc.wdev.platform.commons.data.jdbc.dialect.DbDialect;
import cc.wdev.platform.commons.data.jdbc.utils.JdbcUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import static cc.wdev.platform.commons.autoconfigure.data.properties.DataCoreProperties.PREFIX;

/**
 * @author elvea
 */
@Slf4j
@EnableConfigurationProperties({DataCoreProperties.class, DataJdbcProperties.class})
public class DataCoreAutoConfiguration {

    public DataCoreAutoConfiguration(DataCoreProperties dataCoreProperties, DataJdbcProperties dataJdbcProperties) {
        log.info("DataCoreAutoConfiguration is enabled");
        log.info("DataCoreAutoConfiguration core {}", dataCoreProperties.isEnabled() ? "enabled" : "disabled");
        log.info("DataCoreAutoConfiguration jdbc {}", dataJdbcProperties.isEnabled() ? "enabled" : "disabled");
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
    public UserAuditorAware userAuditorAware() {
        return new UserAuditorAware();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass({JdbcTemplate.class})
    @ConditionalOnProperty(prefix = DataJdbcProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
    public DbDialect dbDialect(JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.execute(JdbcUtils::getDialect);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass({JdbcTemplate.class})
    @ConditionalOnProperty(prefix = DataJdbcProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
    public Dao dao(JdbcTemplate jdbcTemplate) {
        return new Dao(jdbcTemplate);
    }

}

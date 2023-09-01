package cn.elvea.platform.commons.core.autoconfigure.data.jdbc;

import cn.elvea.platform.commons.core.autoconfigure.data.jdbc.properties.CustomJdbcProperties;
import cn.elvea.platform.commons.core.data.core.Dao;
import cn.elvea.platform.commons.core.data.core.dialect.DbDialect;
import cn.elvea.platform.commons.core.data.core.utils.JdbcUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(CustomJdbcProperties.class)
@ConditionalOnProperty(prefix = CustomJdbcProperties.PREFIX, name = "enabled", havingValue = "true")
public class CustomJdbcAutoConfiguration {

    public CustomJdbcAutoConfiguration() {
        log.info("CustomJdbcAutoConfiguration is enabled.");
    }

    /**
     * @see DbDialect
     */
    @Bean
    @ConditionalOnMissingBean
    public DbDialect dbDialect(JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.execute(JdbcUtils::getDialect);
    }

    /**
     * @see Dao
     */
    @Bean
    @ConditionalOnMissingBean
    public Dao dao(JdbcTemplate jdbcTemplate) {
        return new Dao(jdbcTemplate);
    }

}

package cn.elvea.platform.commons.core.autoconfigure.ds;

import cn.elvea.platform.commons.core.autoconfigure.ds.properties.CustomDataSourceProperties;
import cn.elvea.platform.commons.core.constants.DataSourceConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@AutoConfiguration(before = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ConditionalOnProperty(prefix = CustomDataSourceProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties(CustomDataSourceProperties.class)
public class CustomDataSourceAutoConfiguration {

    public CustomDataSourceAutoConfiguration(CustomDataSourceProperties customDataSourceProperties) {
        log.info("CustomDataSourceAutoConfiguration is enabled.");
        if (customDataSourceProperties.getMasterSlaveEnabled()) {
            log.info("CustomDataSourceAutoConfiguration master-slave is enabled.");
        } else {
            log.info("CustomDataSourceAutoConfiguration master-slave is disabled.");
        }
    }

    /**
     * 默认数据源
     */
    @Bean
    @Primary
    public DataSource dataSource(
            @Autowired(required = false) @Qualifier(DataSourceConstants.DS_MASTER) DataSource masterDataSource,
            @Autowired(required = false) @Qualifier(DataSourceConstants.DS_SLAVE) DataSource slaveDataSource
    ) {
        return masterDataSource;
    }

    /**
     * 主数据源配置
     */
    @Bean(name = DataSourceConstants.DS_CONFIG_MASTER)
    @ConfigurationProperties("spring.datasource.master")
    @ConditionalOnProperty(prefix = CustomDataSourceProperties.DS_MASTER_PREFIX, name = "enabled", havingValue = "true")
    public DataSourceProperties masterDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = DataSourceConstants.DS_MASTER)
    @ConditionalOnProperty(prefix = CustomDataSourceProperties.DS_MASTER_PREFIX, name = "enabled", havingValue = "true")
    public DataSource masterDataSource(@Qualifier(DataSourceConstants.DS_CONFIG_MASTER) DataSourceProperties masterDataSourceProperties) {
        log.debug("Creating Master DataSource...");
        return masterDataSourceProperties.initializeDataSourceBuilder().build();
    }

    /**
     * 从数据源配置
     */
    @Bean(name = DataSourceConstants.DS_CONFIG_SLAVE)
    @ConfigurationProperties("spring.datasource.slave")
    @ConditionalOnProperty(prefix = CustomDataSourceProperties.DS_SLAVE_PREFIX, name = "enabled", havingValue = "true")
    public DataSourceProperties slaveDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = DataSourceConstants.DS_SLAVE)
    @ConditionalOnProperty(prefix = CustomDataSourceProperties.DS_SLAVE_PREFIX, name = "enabled", havingValue = "true")
    public DataSource slaveDataSource(@Qualifier(DataSourceConstants.DS_CONFIG_SLAVE) DataSourceProperties slaveDataSourceProperties) {
        log.debug("Creating Slave DataSource...");
        return slaveDataSourceProperties.initializeDataSourceBuilder().build();
    }

    /**
     * 定时任务数据源配置
     */
    @Bean(name = DataSourceConstants.DS_CONFIG_JOB)
    @ConfigurationProperties("spring.datasource.job")
    @ConditionalOnProperty(prefix = CustomDataSourceProperties.DS_JOB_PREFIX, name = "enabled", havingValue = "true")
    public DataSourceProperties jobDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = DataSourceConstants.DS_JOB)
    @ConditionalOnProperty(prefix = CustomDataSourceProperties.DS_JOB_PREFIX, name = "enabled", havingValue = "true")
    public DataSource jobDataSource(@Qualifier(DataSourceConstants.DS_CONFIG_JOB) DataSourceProperties jobDataSourceProperties) {
        log.debug("Creating Job DataSource...");
        return jobDataSourceProperties.initializeDataSourceBuilder().build();
    }

}

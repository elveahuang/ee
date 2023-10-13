package cn.elvea.platform.commons.core.autoconfigure.scheduling.quartz;

import cn.elvea.platform.commons.core.autoconfigure.scheduling.quartz.properties.CustomQuartzProperties;
import cn.elvea.platform.commons.core.constants.DataSourceConstants;
import cn.elvea.platform.commons.core.scheduling.quartz.QuartzJobManager;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@AutoConfiguration(before = {QuartzAutoConfiguration.class})
@ConditionalOnClass({Scheduler.class, SchedulerFactoryBean.class, PlatformTransactionManager.class})
@ConditionalOnProperty(prefix = CustomQuartzProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties({QuartzProperties.class, CustomQuartzProperties.class})
public class CustomQuartzAutoConfiguration {

    public CustomQuartzAutoConfiguration() {
        log.info("CustomQuartzAutoConfiguration is enabled.");
    }

    @Bean
    @Order(0)
    @ConditionalOnProperty(prefix = CustomQuartzProperties.CLUSTER_PREFIX, name = "enabled", havingValue = "true")
    public SchedulerFactoryBeanCustomizer dataSourceCustomizer(
            @Autowired(required = false) @Qualifier(DataSourceConstants.DS_MASTER) DataSource masterDataSource,
            @Autowired(required = false) @Qualifier(DataSourceConstants.DS_JOB) DataSource jobDataSource
    ) {
        return (schedulerFactoryBean) -> {
            DataSource dataSourceToUse = getDataSource(masterDataSource, jobDataSource);
            schedulerFactoryBean.setDataSource(dataSourceToUse);
        };
    }

    private DataSource getDataSource(DataSource dataSource, DataSource jobDataSource) {
        return (jobDataSource != null) ? jobDataSource : dataSource;
    }

    @Bean
    @ConditionalOnMissingBean
    public SchedulerFactoryBean quartzScheduler(QuartzProperties properties,
                                                ObjectProvider<SchedulerFactoryBeanCustomizer> customizers,
                                                ApplicationContext applicationContext) {
        SpringBeanJobFactory jobFactory = new SpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);

        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(jobFactory);
        if (properties.getSchedulerName() != null) {
            schedulerFactoryBean.setSchedulerName(properties.getSchedulerName());
        }
        schedulerFactoryBean.setAutoStartup(properties.isAutoStartup());
        schedulerFactoryBean.setStartupDelay((int) properties.getStartupDelay().getSeconds());
        schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(properties.isWaitForJobsToCompleteOnShutdown());
        schedulerFactoryBean.setOverwriteExistingJobs(properties.isOverwriteExistingJobs());
        if (!properties.getProperties().isEmpty()) {
            schedulerFactoryBean.setQuartzProperties(asProperties(properties.getProperties()));
        }
        customizers.orderedStream().forEach((customizer) -> customizer.customize(schedulerFactoryBean));
        return schedulerFactoryBean;
    }

    @Bean
    @ConditionalOnMissingBean
    public QuartzJobManager quartzJobManager(Scheduler scheduler) {
        return new QuartzJobManager(scheduler);
    }

    private Properties asProperties(Map<String, String> source) {
        Properties properties = new Properties();
        properties.putAll(source);
        return properties;
    }

}

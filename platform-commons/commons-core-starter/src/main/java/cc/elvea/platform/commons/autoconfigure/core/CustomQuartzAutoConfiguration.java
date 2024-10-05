package cc.elvea.platform.commons.autoconfigure.core;

import cc.elvea.platform.commons.annotations.ds.JobDataSource;
import cc.elvea.platform.commons.annotations.ds.JobTransactionManager;
import cc.elvea.platform.commons.autoconfigure.core.properties.CustomQuartzProperties;
import cc.elvea.platform.commons.core.quartz.QuartzJobManager;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Calendar;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.sql.init.dependency.DatabaseInitializationDependencyConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;

/**
 * @author elvea
 * @since 24.1.0
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
    @ConditionalOnMissingBean
    public SchedulerFactoryBean quartzScheduler(QuartzProperties properties,
                                                ObjectProvider<SchedulerFactoryBeanCustomizer> customizers,
                                                ObjectProvider<JobDetail> jobDetails,
                                                Map<String, Calendar> calendars,
                                                ObjectProvider<Trigger> triggers,
                                                ApplicationContext applicationContext) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        SpringBeanJobFactory jobFactory = new SpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
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
        schedulerFactoryBean.setJobDetails(jobDetails.orderedStream().toArray(JobDetail[]::new));
        schedulerFactoryBean.setCalendars(calendars);
        schedulerFactoryBean.setTriggers(triggers.orderedStream().toArray(Trigger[]::new));
        customizers.orderedStream().forEach((customizer) -> customizer.customize(schedulerFactoryBean));
        return schedulerFactoryBean;
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnProperty(prefix = "spring.quartz", name = "job-store-type", havingValue = "jdbc")
    @Import(DatabaseInitializationDependencyConfigurer.class)
    protected static class JdbcStoreTypeConfiguration {

        public JdbcStoreTypeConfiguration() {
            log.info("JdbcStoreTypeConfiguration is enabled.");
        }

        @Bean
        public SchedulerFactoryBeanCustomizer dataSourceCustomizer(DataSource dataSource,
                                                                   @JobDataSource ObjectProvider<DataSource> jobDataSource,
                                                                   ObjectProvider<PlatformTransactionManager> transactionManager,
                                                                   @JobTransactionManager ObjectProvider<PlatformTransactionManager> jobTransactionManager) {
            return (schedulerFactoryBean) -> {
                schedulerFactoryBean.setDataSource(getDataSource(dataSource, jobDataSource));
                PlatformTransactionManager txManager = getTransactionManager(transactionManager, jobTransactionManager);
                if (txManager != null) {
                    schedulerFactoryBean.setTransactionManager(txManager);
                }
            };
        }

        private DataSource getDataSource(DataSource dataSource, ObjectProvider<DataSource> quartzDataSource) {
            DataSource dataSourceIfAvailable = quartzDataSource.getIfAvailable();
            return (dataSourceIfAvailable != null) ? dataSourceIfAvailable : dataSource;
        }

        private PlatformTransactionManager getTransactionManager(
                ObjectProvider<PlatformTransactionManager> transactionManager,
                ObjectProvider<PlatformTransactionManager> jobTransactionManager) {
            PlatformTransactionManager txManager = jobTransactionManager.getIfAvailable();
            return (txManager != null) ? txManager : transactionManager.getIfUnique();
        }

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

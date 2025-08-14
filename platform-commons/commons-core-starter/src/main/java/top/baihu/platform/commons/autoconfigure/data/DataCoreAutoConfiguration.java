package top.baihu.platform.commons.autoconfigure.data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.baihu.platform.commons.autoconfigure.data.properties.DataCoreProperties;
import top.baihu.platform.commons.data.core.auditor.UserAuditorAware;

import static top.baihu.platform.commons.autoconfigure.data.properties.DataCoreProperties.PREFIX;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(DataCoreProperties.class)
@ConditionalOnProperty(prefix = PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class DataCoreAutoConfiguration {

    public DataCoreAutoConfiguration() {
        log.info("DataCoreAutoConfiguration is enabled");
    }

    @Bean
    @ConditionalOnMissingBean
    public UserAuditorAware userAuditorAware() {
        return new UserAuditorAware();
    }

}

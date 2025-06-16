package cc.elvea.platform.commons.autoconfigure.extensions;

import cc.elvea.platform.commons.autoconfigure.extensions.properties.SeleniumProperties;
import cc.elvea.platform.commons.extensions.selenium.DefaultSeleniumFactory;
import cc.elvea.platform.commons.extensions.selenium.SeleniumConfig;
import cc.elvea.platform.commons.extensions.selenium.SeleniumFactory;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.manager.SeleniumManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({SeleniumProperties.class})
@ConditionalOnClass({SeleniumManager.class})
@ConditionalOnProperty(prefix = SeleniumProperties.PREFIX, name = "enabled", havingValue = "true")
public class SeleniumAutoConfiguration {

    public SeleniumAutoConfiguration() {
        log.info("SeleniumAutoConfiguration is enabled");
    }

    @Bean
    @ConditionalOnMissingBean
    public SeleniumFactory seleniumFactory(SeleniumProperties properties) {
        SeleniumConfig config = SeleniumConfig.builder()
            .driverType(properties.getDriverType())
            .driverVersion(properties.getDriverVersion())
            .proxy(properties.getProxy())
            .build();
        SeleniumFactory factory = new DefaultSeleniumFactory(config);
        factory.initialize();
        return factory;
    }

}

package top.baihu.platform.commons.autoconfigure.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import top.baihu.platform.commons.autoconfigure.core.properties.TestProperties;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({TestProperties.class})
@ConditionalOnProperty(prefix = TestProperties.PREFIX, name = "enabled", havingValue = "true")
public class TestAutoConfiguration {

    public TestAutoConfiguration(TestProperties properties) {
        log.info("TestAutoConfiguration is enabled");
        log.info("Test email - {}", properties.getEmail());
        log.info("Test mobile - {}", properties.getMobile());
    }

}

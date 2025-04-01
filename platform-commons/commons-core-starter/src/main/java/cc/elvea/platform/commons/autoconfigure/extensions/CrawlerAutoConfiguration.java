package cc.elvea.platform.commons.autoconfigure.extensions;

import cc.elvea.platform.commons.autoconfigure.extensions.properties.CrawlerProperties;
import cc.elvea.platform.commons.extensions.crawler.CrawlerConfig;
import cc.elvea.platform.commons.extensions.crawler.CrawlerManager;
import cc.elvea.platform.commons.extensions.crawler.DefaultCrawlerManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriverService;
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
@EnableConfigurationProperties({CrawlerProperties.class})
@ConditionalOnClass({ChromeDriverService.class})
@ConditionalOnProperty(prefix = CrawlerProperties.PREFIX, name = "enabled", havingValue = "true")
public class CrawlerAutoConfiguration {

    public CrawlerAutoConfiguration() {
        log.info("CrawlerAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean
    public CrawlerManager crawlerManager(CrawlerProperties properties) {
        CrawlerConfig config = CrawlerConfig.builder()
                .selenium(properties.getSelenium())
                .build();
        return new DefaultCrawlerManager(config);
    }

}

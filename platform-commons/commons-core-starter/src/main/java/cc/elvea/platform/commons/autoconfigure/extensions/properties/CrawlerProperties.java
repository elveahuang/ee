package cc.elvea.platform.commons.autoconfigure.extensions.properties;

import cc.elvea.platform.commons.extensions.crawler.CrawlerConfig;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author elvea
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(CrawlerProperties.PREFIX)
public class CrawlerProperties {

    public static final String PREFIX = "platform.crawler";

    private boolean enabled = false;

    @NestedConfigurationProperty
    private CrawlerConfig.Selenium selenium = CrawlerConfig.Selenium.builder().build();

}

package cc.elvea.platform.commons.extensions.crawler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrawlerConfig implements Serializable {

    @Builder.Default
    private CrawlerConfig.Selenium selenium = CrawlerConfig.Selenium.builder().build();

    @Data
    @Builder
    public static class Selenium {
        private String driverPath;
    }

}

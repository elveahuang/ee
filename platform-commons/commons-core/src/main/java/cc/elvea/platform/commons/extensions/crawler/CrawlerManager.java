package cc.elvea.platform.commons.extensions.crawler;

import org.openqa.selenium.chrome.ChromeDriverService;

/**
 * @author elvea
 */
public interface CrawlerManager {

    /**
     * @return {@see CrawlerConfig}
     */
    CrawlerConfig config();

    /**
     * @return {@see ChromeDriverService.Builder}
     */
    ChromeDriverService getChromeDriverService();

    /**
     * @return {@see ChromeDriverService.Builder}
     */
    ChromeDriverService getChromeDriverService(String driverPath);

}

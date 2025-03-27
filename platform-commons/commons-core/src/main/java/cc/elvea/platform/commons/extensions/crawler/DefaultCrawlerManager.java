package cc.elvea.platform.commons.extensions.crawler;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;

/**
 * @author elvea
 */
@Slf4j
public record DefaultCrawlerManager(CrawlerConfig config) implements CrawlerManager {

    /**
     * @see CrawlerManager#getChromeDriverService()
     */
    @Override
    public ChromeDriverService getChromeDriverService() {
        return getChromeDriverService(this.config().getSelenium().getDriverPath());
    }

    /**
     * @see CrawlerManager#getChromeDriverService(String)
     */
    @Override
    public ChromeDriverService getChromeDriverService(String driverPath) {
        log.info("createChromeDriverServiceBuilder {}", driverPath);
        return new ChromeDriverService.Builder().usingDriverExecutable(new File(driverPath)).build();
    }

}

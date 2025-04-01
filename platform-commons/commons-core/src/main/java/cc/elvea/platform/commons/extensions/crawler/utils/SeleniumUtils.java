package cc.elvea.platform.commons.extensions.crawler.utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;

@Slf4j
public abstract class SeleniumUtils {

    public static ChromeOptions getDefaultChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        return options;
    }

    public static ChromeOptions getMobileChromeOptions() {
        ChromeOptions options = getDefaultChromeOptions();
        options.setExperimentalOption("mobileEmulation", Map.of("deviceName", "iPhone X"));
        return options;
    }

    public static void closeDriver(WebDriver driver) {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                log.error("Error quitting driver", e);
            }
        }
    }

}

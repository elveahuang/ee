package cc.wdev.platform.commons.utils;

import cc.wdev.dev.webapp.BaseTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class SecurityUtilsTests extends BaseTests {

    @Test
    public void passwordTest() {
        String password = SecurityUtils.encode("test");
        log.info("password: {}", password);
        Assertions.assertTrue(SecurityUtils.matches("test", password));
    }

}

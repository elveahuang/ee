package cc.wdev.platform.commons.utils;

import cc.wdev.dev.webapp.BaseTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class SecurityUtilsTests extends BaseTests {

    @Test
    public void passwordTest() {
        String plainPassword, encryptedPassword;

        plainPassword = "admin";
        encryptedPassword = SecurityUtils.encode(plainPassword);
        log.info("plainPassword : {} | encryptedPassword : {}", plainPassword, encryptedPassword);
        Assertions.assertTrue(SecurityUtils.matches(plainPassword, encryptedPassword));

        plainPassword = "test";
        encryptedPassword = SecurityUtils.encode(plainPassword);
        log.info("plainPassword : {} | encryptedPassword : {}", plainPassword, encryptedPassword);
        Assertions.assertTrue(SecurityUtils.matches(plainPassword, encryptedPassword));
    }

}

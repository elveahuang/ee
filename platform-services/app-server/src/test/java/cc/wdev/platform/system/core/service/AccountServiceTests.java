package cc.wdev.platform.system.core.service;

import cc.wdev.platform.BaseTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
@Slf4j
public class AccountServiceTests extends BaseTests {

    @Autowired
    AccountService accountService;

    @Test
    public void baseTest() {
        Assertions.assertNotNull(accountService);
    }

}

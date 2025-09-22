package cc.wdev.dev.webapp.mybatis.service;

import cc.wdev.dev.webapp.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class MpUserServiceTests extends BaseTests {

    @Autowired
    MpUserService mpUserService;

    @Test
    public void baseTest() {
        Assertions.assertNotNull(this.mpUserService);
    }

}

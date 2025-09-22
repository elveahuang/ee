package cc.wdev.dev.webapp.jpa.service;

import cc.wdev.dev.webapp.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class JpaUserServiceTests extends BaseTests {

    @Autowired
    JpaUserService jpaUserService;

    @Test
    public void baseTest() {
        Assertions.assertNotNull(this.jpaUserService);
    }

}

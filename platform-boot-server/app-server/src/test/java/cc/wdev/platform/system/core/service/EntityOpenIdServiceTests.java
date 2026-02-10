package cc.wdev.platform.system.core.service;

import cc.wdev.platform.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class EntityOpenIdServiceTests extends BaseTests {

    @Autowired
    EntityOpenIdService entityOpenIdService;

    @Test
    public void baseTest() {
        Assertions.assertNotNull(entityOpenIdService);
    }

}

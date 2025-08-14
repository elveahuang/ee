package top.baihu.platform.system.core.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.system.BaseTests;

/**
 * @author elvea
 */
public class AuthorityServiceTests extends BaseTests {

    @Autowired
    AuthorityService authorityService;

    @Test
    public void loadCache() {
        this.authorityService.refreshCache();
    }

    @Test
    public void clearCache() {
        this.authorityService.clearCache();
    }

    @Test
    public void base() {
        Assertions.assertNotNull(authorityService);
        authorityService.findByUserId(BaseTests.DEFAULT_USER_ID);
    }

}

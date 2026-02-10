package cc.wdev.platform.security;

import cc.wdev.platform.BaseTests;
import cc.wdev.platform.security.core.service.CustomAccountDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author elvea
 */
@Slf4j
public class CustomAccountDetailsServiceTests extends BaseTests {

    @Autowired
    CustomAccountDetailsService customAccountDetailsService;

    @Test
    public void base() {
        Assertions.assertNotNull(this.customAccountDetailsService);
        UserDetails userDetails = customAccountDetailsService.loadUserByUsername("admin");
        Assertions.assertNotNull(userDetails);
    }

}

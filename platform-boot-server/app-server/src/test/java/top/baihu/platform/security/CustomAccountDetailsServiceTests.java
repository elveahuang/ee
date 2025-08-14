package top.baihu.platform.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import top.baihu.platform.BaseTests;

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

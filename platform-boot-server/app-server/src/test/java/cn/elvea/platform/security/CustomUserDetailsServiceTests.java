package cn.elvea.platform.security;

import cn.elvea.platform.BaseTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class CustomUserDetailsServiceTests extends BaseTests {

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Test
    public void base() {
        Assertions.assertNotNull(this.userDetailsService);
        UserDetails userDetails = userDetailsService.loadUserByUsername("admin");
        Assertions.assertNotNull(userDetails);
    }

}

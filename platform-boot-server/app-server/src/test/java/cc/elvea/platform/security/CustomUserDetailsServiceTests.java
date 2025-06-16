package cc.elvea.platform.security;

import cc.elvea.platform.BaseTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author elvea
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

package cc.wdev.dev.webapp.jpa.repository;

import cc.wdev.dev.webapp.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class JpaUserRepositoryTests extends BaseTests {

    @Autowired
    JpaUserRepository jpaUserRepository;

    @Test
    public void baseTest() {
        Assertions.assertNotNull(this.jpaUserRepository);
    }

}

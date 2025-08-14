package top.baihu.platform.commons.utils;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import top.baihu.platform.commons.utils.builder.Builder;

/**
 * @author elvea
 */
public class BuilderTests {

    @Test
    public void test() {
        User user = Builder.of(User::new)
            .with(User::setUsername, "mike")
            .build();
        Assertions.assertEquals("mike", user.getUsername());
    }

    @Getter
    @Setter
    static class User {

        private String username;

    }

}

package cc.elvea.platform.system.core.mapper;

import cc.elvea.platform.system.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class UserMapperTests extends BaseTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void test() {
        Assertions.assertNotNull(this.userMapper);
    }

}

package cn.elvea.platform.system.core.mapper;

import cn.elvea.platform.system.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 * @since 0.0.1
 */
public class UserMapperTests extends BaseTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void test() {
        Assertions.assertNotNull(this.userMapper);
    }

}

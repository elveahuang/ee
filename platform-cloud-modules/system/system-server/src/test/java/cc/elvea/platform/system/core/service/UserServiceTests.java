package cc.elvea.platform.system.core.service;

import cc.elvea.platform.system.BaseTests;
import cc.elvea.platform.system.core.model.dto.UserRegisterDto;
import cc.elvea.platform.system.core.model.entity.UserEntity;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author elvea
 */
public class UserServiceTests extends BaseTests {

    @Autowired
    private UserService userService;

    @Test
    public void testFindById() {
        UserEntity entity = this.userService.findCacheById(1L);
        Assertions.assertNotNull(entity);
    }

    @Test
    public void testFindByIds() {
        List<UserEntity> entityList = this.userService.findByIds(Lists.list(1L));
        Assertions.assertNotNull(entityList);
    }

    @Test
    public void findByUsernameTest() {
        UserEntity entity = this.userService.findByUsername("admin");
        Assertions.assertNotNull(entity);
    }

    @Test
    public void testRegister() {
        UserRegisterDto dto = UserRegisterDto.builder().build();
        this.userService.register(dto);
    }

}

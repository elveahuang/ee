package cc.elvea.platform.system.core.service;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.core.cache.CacheKey;
import cc.elvea.platform.commons.core.sequence.Sequence;
import cc.elvea.platform.system.core.model.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
@Slf4j
public class UserServiceTests extends BaseTests {

    @Autowired
    UserService userService;

    @Autowired
    Sequence sequence;

    @Test
    public void base() {
        log.info("getCurrentEntityClass - {}", this.userService.currentEntityClass());
        log.info("getCurrentEntityIdClass - {}", this.userService.currentEntityIdClass());
        Assertions.assertNotNull(this.userService);
        Assertions.assertNotNull(this.userService.getEntityIdClass());
        Assertions.assertNotNull(this.userService.currentEntityClass());
        Assertions.assertNotNull(this.userService.currentEntityIdClass());
        Assertions.assertNotNull(this.userService.findCacheById(1L));
        Assertions.assertNotNull(this.userService.findCacheByIds(Lists.newArrayList(1L)));
    }

    @Test
    public void findTest() {
        UserEntity entity = this.userService.findByUsername("admin");
        Assertions.assertNotNull(entity);
    }

    @Test
    public void crud() {
        UserEntity user = new UserEntity();
        user.setUsername(String.valueOf(this.sequence.nextId()));
        this.userService.insert(user);

        CacheKey cacheKey = this.userService.getCacheKeyGenerator().key(user.getId());

        Assertions.assertNotNull(this.userService.findByCacheKey(cacheKey));

        this.userService.updateById(user);

        Assertions.assertNull(this.userService.findByCacheKey(cacheKey));

        this.userService.findCacheById(user.getId());
        Assertions.assertNotNull(this.userService.findByCacheKey(cacheKey));
    }

}

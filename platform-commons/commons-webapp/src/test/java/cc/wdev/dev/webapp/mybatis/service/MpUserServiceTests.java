package cc.wdev.dev.webapp.mybatis.service;

import cc.wdev.dev.webapp.BaseTests;
import cc.wdev.webapp.mybatis.domain.entity.MpUserEntity;
import cc.wdev.webapp.mybatis.service.MpUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class MpUserServiceTests extends BaseTests {

    @Autowired
    MpUserService mpUserService;

    @Test
    public void baseTest() {
        Assertions.assertNotNull(this.mpUserService);
    }

    @Test
    public void versionTest() {
        MpUserEntity user = new MpUserEntity();
        user.setUsername("admin");
        this.mpUserService.save(user);

        Long id = user.getId();
        Assertions.assertNotNull(id);

        user = this.mpUserService.findById(id);

        this.mpUserService.updateById(user);
        Assertions.assertNotNull(user.getVersion());
    }

}

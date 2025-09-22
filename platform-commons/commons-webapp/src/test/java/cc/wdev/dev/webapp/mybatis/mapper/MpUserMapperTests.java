package cc.wdev.dev.webapp.mybatis.mapper;

import cc.wdev.dev.webapp.BaseTests;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class MpUserMapperTests extends BaseTests {

    @Autowired
    MpUserMapper mpUserMapper;

    @Test
    public void baseTest() {
        Assertions.assertNotNull(this.mpUserMapper);
    }

    @Test
    public void tenantTest() {
        Assertions.assertNotNull(this.mpUserMapper.selectList(Wrappers.lambdaQuery()));
    }

}

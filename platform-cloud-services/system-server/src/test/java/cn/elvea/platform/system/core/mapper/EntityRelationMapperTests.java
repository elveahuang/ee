package cn.elvea.platform.system.core.mapper;

import cn.elvea.platform.system.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * EntityRelationMapperTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class EntityRelationMapperTests extends BaseTests {

    @Autowired
    EntityRelationMapper entityRelationMapper;

    @Test
    public void test() {
        Assertions.assertNotNull(this.entityRelationMapper);
    }

}

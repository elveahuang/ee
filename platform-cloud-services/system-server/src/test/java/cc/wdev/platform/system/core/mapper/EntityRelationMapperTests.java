package cc.wdev.platform.system.core.mapper;

import cc.wdev.platform.system.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * EntityRelationMapperTests
 *
 * @author elvea
 */
public class EntityRelationMapperTests extends BaseTests {

    @Autowired
    EntityRelationMapper entityRelationMapper;

    @Test
    public void test() {
        Assertions.assertNotNull(this.entityRelationMapper);
    }

}

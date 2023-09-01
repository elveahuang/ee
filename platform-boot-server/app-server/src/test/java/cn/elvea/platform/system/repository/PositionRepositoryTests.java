package cn.elvea.platform.system.repository;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.commons.core.utils.CollectionUtils;
import cn.elvea.platform.system.core.model.entity.PositionEntity;
import cn.elvea.platform.system.core.repository.PositionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
 */
public class PositionRepositoryTests extends BaseTests {

    @Autowired
    PositionRepository positionRepository;

    @Test
    public void test() {
        Assertions.assertNotNull(this.positionRepository);

        List<PositionEntity> list = this.positionRepository.findAll();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(list));
    }

}

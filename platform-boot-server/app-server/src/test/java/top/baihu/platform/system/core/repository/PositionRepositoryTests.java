package top.baihu.platform.system.core.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.BaseTests;
import top.baihu.platform.commons.utils.CollectionUtils;
import top.baihu.platform.system.core.domain.entity.PositionEntity;

import java.util.List;

/**
 * @author elvea
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

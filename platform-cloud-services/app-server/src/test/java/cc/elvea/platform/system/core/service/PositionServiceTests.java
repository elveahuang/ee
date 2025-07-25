package cc.elvea.platform.system.core.service;

import cc.elvea.platform.system.BaseTests;
import cc.elvea.platform.system.core.model.dto.PositionDto;
import cc.elvea.platform.system.core.model.dto.PositionSaveDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class PositionServiceTests extends BaseTests {

    @Autowired
    PositionService positionService;

    @Test
    public void loadCache() {
        this.positionService.refreshCache();
    }

    @Test
    public void clearCache() {
        this.positionService.clearCache();
    }

    @Test
    public void base() {
        Long parentId = BaseTests.DEFAULT_POSITION_ID;
        for (int i = 0; i <= 10; i++) {
            PositionSaveDto saveDto = PositionSaveDto.builder().title("Level_" + (i + 2)).parentId(parentId).build();
            PositionDto dto = this.positionService.savePosition(saveDto);
            Assertions.assertNotNull(dto);
            parentId = dto.getId();
        }
    }

}

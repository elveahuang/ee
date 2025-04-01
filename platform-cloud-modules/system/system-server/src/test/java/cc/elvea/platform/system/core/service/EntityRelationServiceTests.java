package cc.elvea.platform.system.core.service;

import cc.elvea.platform.system.BaseTests;
import cc.elvea.platform.system.core.model.dto.*;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class EntityRelationServiceTests extends BaseTests {

    @Autowired
    EntityRelationService entityRelationService;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    PositionService positionService;

    @Autowired
    UserService userService;

    @Test
    public void test() {
        Assertions.assertNotNull(this.entityRelationService);
    }

    @Test
    public void entityTest() {
        Long parentPositionId = BaseTests.DEFAULT_POSITION_ID;
        for (int i = 0; i <= 10; i++) {
            PositionSaveDto saveDto = PositionSaveDto.builder().title("Level_" + (i + 2)).parentId(parentPositionId).build();
            PositionDto dto = this.positionService.savePosition(saveDto);
            Assertions.assertNotNull(dto);
            parentPositionId = dto.getId();
        }

        Long parentOrganizationId = BaseTests.DEFAULT_ORGANIZATION_ID;
        for (int i = 0; i <= 10; i++) {
            OrganizationSaveDto saveDto = OrganizationSaveDto.builder()
                    .title("Level_" + (i + 2))
                    .parentId(parentOrganizationId)
                    .build();
            OrganizationDto dto = this.organizationService.saveOrganization(saveDto);
            Assertions.assertNotNull(dto);
            parentOrganizationId = dto.getId();
        }

        for (int i = 0; i <= 10; i++) {
            UserSaveDto saveDto = UserSaveDto.builder()
                    .username("user_" + (i + 2) + "_" + this.userService.generateIdAsString())
                    .positionIdList(Lists.newArrayList(parentPositionId))
                    .organizationIdList(Lists.newArrayList(parentOrganizationId))
                    .build();
            UserDto dto = this.userService.saveUser(saveDto);
            Assertions.assertNotNull(dto);
        }

    }

}

package top.baihu.platform.system.core.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.system.BaseTests;
import top.baihu.platform.system.core.domain.dto.OrganizationDto;
import top.baihu.platform.system.core.domain.dto.OrganizationSaveDto;

/**
 * @author elvea
 */
public class OrganizationServiceTests extends BaseTests {

    @Autowired
    OrganizationService organizationService;

    @Test
    public void loadCache() {
        this.organizationService.refreshCache();
    }

    @Test
    public void clearCache() {
        this.organizationService.clearCache();
    }

    @Test
    public void base() {
        Long parentId = BaseTests.DEFAULT_ORGANIZATION_ID;
        for (int i = 0; i <= 10; i++) {
            OrganizationSaveDto saveDto = OrganizationSaveDto.builder().title("Level_" + (i + 2)).parentId(parentId).build();
            OrganizationDto dto = this.organizationService.saveOrganization(saveDto);
            Assertions.assertNotNull(dto);
            parentId = dto.getId();
        }
    }

}

package top.baihu.platform.system.core.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.BaseTests;
import top.baihu.platform.commons.core.sequence.Sequence;
import top.baihu.platform.system.core.domain.dto.OrganizationDto;
import top.baihu.platform.system.core.domain.dto.OrganizationSaveDto;

/**
 * @author elvea
 */
public class OrganizationServiceTests extends BaseTests {

    @Autowired
    OrganizationService organizationService;

    @Autowired
    Sequence sequence;

    @Test
    public void baseTest() {
        Assertions.assertNotNull(this.organizationService);

        OrganizationSaveDto l1SaveDto = OrganizationSaveDto.builder()
            .code("Dpt_1_" + this.sequence.generateCode())
            .title("Dpt - 1 - " + this.sequence.generateCode())
            .parentId(1L)
            .build();
        OrganizationDto l1Organization = this.organizationService.saveOrganization(l1SaveDto);
        Assertions.assertNotNull(l1Organization.getId());

        OrganizationSaveDto l2SaveDto = OrganizationSaveDto.builder()
            .code("Dpt_2_" + this.sequence.generateCode())
            .title("Dpt - 2 - " + this.sequence.generateCode())
            .parentId(l1Organization.getId())
            .build();
        OrganizationDto l2Organization = this.organizationService.saveOrganization(l2SaveDto);
        Assertions.assertNotNull(l2Organization.getId());

        OrganizationSaveDto l3SaveDto = OrganizationSaveDto.builder()
            .code("Dpt_3_" + this.sequence.generateCode())
            .title("Dpt - 3 - " + this.sequence.generateCode())
            .parentId(l2Organization.getId())
            .build();
        OrganizationDto l3Organization = this.organizationService.saveOrganization(l3SaveDto);
        Assertions.assertNotNull(l3Organization.getId());

        OrganizationSaveDto l4SaveDto = OrganizationSaveDto.builder()
            .code("Dpt_4_" + this.sequence.generateCode())
            .title("Dpt - 4 - " + this.sequence.generateCode())
            .parentId(l3Organization.getId())
            .build();
        OrganizationDto l4Organization = this.organizationService.saveOrganization(l4SaveDto);
        Assertions.assertNotNull(l4Organization.getId());
    }

}

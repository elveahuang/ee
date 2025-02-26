package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.system.BaseTests;
import cc.elvea.platform.system.core.model.dto.RoleAuthoritySaveDto;
import cc.elvea.platform.system.core.model.entity.RoleAuthorityEntity;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author elvea
 */
public class RoleAuthorityServiceTests extends BaseTests {

    @Autowired
    RoleAuthorityService roleAuthorityService;

    @Test
    public void test() {
        RoleAuthoritySaveDto dto = RoleAuthoritySaveDto.builder()
                .roleId(1L)
                .authorityIdList(Lists.newArrayList(1L))
                .build();
        roleAuthorityService.saveRoleAuthority(dto);

        List<RoleAuthorityEntity> roleAuthorityEntityList = this.roleAuthorityService.findByRoleId(BaseTests.DEFAULT_USER_ID);
        Assertions.assertTrue(CollectionUtils.isNotEmpty(roleAuthorityEntityList));
        Assertions.assertEquals(roleAuthorityEntityList.size(), 1);
    }

}

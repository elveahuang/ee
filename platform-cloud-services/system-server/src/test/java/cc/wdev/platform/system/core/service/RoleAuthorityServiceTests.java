package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.utils.CollectionUtils;
import cc.wdev.platform.system.BaseTests;
import cc.wdev.platform.system.core.domain.dto.RoleAuthoritySaveDto;
import cc.wdev.platform.system.core.domain.entity.RoleAuthorityEntity;
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

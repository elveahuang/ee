package cn.elvea.platform.system.core.service;

import cn.elvea.platform.commons.core.utils.CollectionUtils;
import cn.elvea.platform.system.BaseTests;
import cn.elvea.platform.system.core.model.dto.UserRoleSaveDto;
import cn.elvea.platform.system.core.model.entity.UserRoleEntity;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
 */
public class UserRoleServiceTests extends BaseTests {

    @Autowired
    UserRoleService userRoleService;

    @Test
    public void test() {
        UserRoleSaveDto dto = UserRoleSaveDto.builder()
                .userId(BaseTests.DEFAULT_USER_ID)
                .roleIdList(Lists.newArrayList(1L))
                .build();
        userRoleService.saveUserRole(dto);

        List<UserRoleEntity> userRoleEntityList = this.userRoleService.findByUserId(BaseTests.DEFAULT_USER_ID);
        Assertions.assertTrue(CollectionUtils.isNotEmpty(userRoleEntityList));
        Assertions.assertEquals(userRoleEntityList.size(), 1);
    }

}

package cc.wdev.platform.system.core.feign;

import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.commons.utils.CollectionUtils;
import cc.wdev.platform.commons.utils.SpringUtils;
import cc.wdev.platform.system.core.domain.converter.AuthorityConverter;
import cc.wdev.platform.system.core.domain.converter.RoleConverter;
import cc.wdev.platform.system.core.domain.converter.UserConverter;
import cc.wdev.platform.system.core.domain.dto.UserLoginDto;
import cc.wdev.platform.system.core.domain.entity.AuthorityEntity;
import cc.wdev.platform.system.core.domain.entity.RoleEntity;
import cc.wdev.platform.system.core.domain.entity.UserEntity;
import cc.wdev.platform.system.core.service.AuthorityService;
import cc.wdev.platform.system.core.service.RoleService;
import cc.wdev.platform.system.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
public class UserFeignClientImpl implements UserFeignClient {

    private final UserService userService;

    private final AuthorityService authorityService;

    private final RoleService roleService;

    @Override
    public R<UserLoginDto> findByUsername(@RequestParam(value = "username") String username) {
        UserEntity entity = userService.findByUsername(username);
        if (entity != null) {
            UserLoginDto user = SpringUtils.getBean(UserConverter.class).entity2UserLoginDto(entity);

            List<AuthorityEntity> authorityEntityList = authorityService.findByUserId(user.getId());
            if (CollectionUtils.isNotEmpty(authorityEntityList)) {
                user.setAuthorities(SpringUtils.getBean(AuthorityConverter.class).entityListToDtoList(authorityEntityList));
            }

            List<RoleEntity> roleEntityList = roleService.findRoleByUserId(user.getId());
            if (CollectionUtils.isNotEmpty(roleEntityList)) {
                user.setRoles(SpringUtils.getBean(RoleConverter.class).entityListToDtoList(roleEntityList));
            }

            return R.success(user);
        }
        return null;
    }

}

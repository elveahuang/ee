package top.baihu.platform.system.core.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.baihu.platform.commons.domain.R;
import top.baihu.platform.commons.utils.CollectionUtils;
import top.baihu.platform.commons.utils.SpringUtils;
import top.baihu.platform.system.core.domain.converter.AuthorityConverter;
import top.baihu.platform.system.core.domain.converter.RoleConverter;
import top.baihu.platform.system.core.domain.converter.UserConverter;
import top.baihu.platform.system.core.domain.dto.UserLoginDto;
import top.baihu.platform.system.core.domain.entity.AuthorityEntity;
import top.baihu.platform.system.core.domain.entity.RoleEntity;
import top.baihu.platform.system.core.domain.entity.UserEntity;
import top.baihu.platform.system.core.service.AuthorityService;
import top.baihu.platform.system.core.service.RoleService;
import top.baihu.platform.system.core.service.UserService;

import java.util.List;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
public class UserApiImpl implements UserApi {

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

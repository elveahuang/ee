package cn.elvea.platform.system.core.api.impl;

import cn.elvea.platform.commons.core.utils.CollectionUtils;
import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.system.core.api.UserApi;
import cn.elvea.platform.system.core.model.converter.AuthorityConverter;
import cn.elvea.platform.system.core.model.converter.RoleConverter;
import cn.elvea.platform.system.core.model.converter.UserConverter;
import cn.elvea.platform.system.core.model.dto.UserLoginDto;
import cn.elvea.platform.system.core.model.entity.AuthorityEntity;
import cn.elvea.platform.system.core.model.entity.RoleEntity;
import cn.elvea.platform.system.core.model.entity.UserEntity;
import cn.elvea.platform.system.core.service.AuthorityService;
import cn.elvea.platform.system.core.service.RoleService;
import cn.elvea.platform.system.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
 */
@AllArgsConstructor
@RestController
public class UserApiImpl implements UserApi {

    private final UserService userService;

    private final AuthorityService authorityService;

    private final RoleService roleService;

    @Override
    public R<UserLoginDto> findByUsername(@RequestParam(value = "username") String username) {
        UserEntity entity = userService.findByUsername(username);
        if (entity != null) {
            UserLoginDto user = UserConverter.INSTANCE.entity2UserLoginDto(entity);

            List<AuthorityEntity> authorityEntityList = authorityService.findByUserId(user.getId());
            if (CollectionUtils.isNotEmpty(authorityEntityList)) {
                user.setAuthorities(AuthorityConverter.INSTANCE.entityListToDtoList(authorityEntityList));
            }

            List<RoleEntity> roleEntityList = roleService.findRoleByUserId(user.getId());
            if (CollectionUtils.isNotEmpty(roleEntityList)) {
                user.setRoles(RoleConverter.INSTANCE.entityListToDtoList(roleEntityList));
            }

            return R.success(user);
        }
        return null;
    }

}

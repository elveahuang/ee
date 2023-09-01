package cn.elvea.platform.system.core.api.impl;

import cn.elvea.platform.commons.core.utils.CollectionUtils;
import cn.elvea.platform.commons.core.utils.ObjectUtils;
import cn.elvea.platform.system.core.api.UserApi;
import cn.elvea.platform.system.core.model.converter.AuthorityConverter;
import cn.elvea.platform.system.core.model.converter.RoleConverter;
import cn.elvea.platform.system.core.model.converter.UserConverter;
import cn.elvea.platform.system.core.model.dto.UserInfoDto;
import cn.elvea.platform.system.core.model.dto.UserLoginDto;
import cn.elvea.platform.system.core.model.entity.AuthorityEntity;
import cn.elvea.platform.system.core.model.entity.RoleEntity;
import cn.elvea.platform.system.core.model.entity.UserEntity;
import cn.elvea.platform.system.core.service.AuthorityService;
import cn.elvea.platform.system.core.service.RoleService;
import cn.elvea.platform.system.core.service.UserService;
import com.lark.oapi.core.utils.Lists;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
 */
@Service
@AllArgsConstructor
public class UserApiImpl implements UserApi {

    private final UserService userService;

    private final AuthorityService authorityService;

    private final RoleService roleService;

    @Override
    public UserInfoDto getUserInfo(String username) {
        UserEntity entity = userService.findByUsername(username);
        return getUserInfoDto(entity);
    }

    @Override
    public UserLoginDto findByUsername(String username) {
        UserEntity entity = userService.findByUsername(username);
        return getUserLoginDto(entity);
    }

    @Override
    public UserLoginDto findByMobile(String mobileCountryCode, String mobileNumber) {
        UserEntity entity = userService.findByMobile(mobileCountryCode, mobileNumber);
        return getUserLoginDto(entity);
    }

    @Override
    public UserLoginDto findByEmail(String email) {
        UserEntity entity = userService.findByEmail(email);
        return getUserLoginDto(entity);
    }

    private UserInfoDto getUserInfoDto(UserEntity entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return null;
        }

        UserInfoDto user = UserConverter.INSTANCE.entity2UserInfoDto(entity);

        // 查询用户所有权限和角色信息
        List<AuthorityEntity> authorityEntityList = authorityService.findByUserId(user.getId());
        List<RoleEntity> roleEntityList = roleService.findRoleByUserId(user.getId());

        // 合并权限和角色统一为权限
        List<String> roles = Lists.newArrayList();
        List<String> authorities = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(authorityEntityList)) {
            authorities.addAll(authorityEntityList.stream().map(AuthorityEntity::getCode).toList());
        }
        if (CollectionUtils.isNotEmpty(roleEntityList)) {
            roles.addAll(roleEntityList.stream().map(RoleEntity::getCode).toList());
            authorities.addAll(roles);
        }

        user.setAuthorities(CollectionUtils.isNotEmpty(authorityEntityList) ? authorities : Collections.emptyList());
        user.setRoles(CollectionUtils.isNotEmpty(roleEntityList) ? roles : Collections.emptyList());
        return user;
    }

    private UserLoginDto getUserLoginDto(UserEntity entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return null;
        }

        UserLoginDto user = UserConverter.INSTANCE.entity2UserLoginDto(entity);
        List<AuthorityEntity> authorityEntityList = authorityService.findByUserId(user.getId());
        if (CollectionUtils.isNotEmpty(authorityEntityList)) {
            user.setAuthorities(AuthorityConverter.INSTANCE.entityListToDtoList(authorityEntityList));
        }
        List<RoleEntity> roleEntityList = roleService.findRoleByUserId(user.getId());
        if (CollectionUtils.isNotEmpty(roleEntityList)) {
            user.setRoles(RoleConverter.INSTANCE.entityListToDtoList(roleEntityList));
        }
        return user;
    }

}

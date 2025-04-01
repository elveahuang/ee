package cc.elvea.platform.system.core.model.converter;

import cc.elvea.platform.system.core.model.dto.UserInfoDto;
import cc.elvea.platform.system.core.model.dto.UserLoginInfoDto;
import cc.elvea.platform.system.core.model.entity.UserEntity;
import cc.elvea.platform.system.core.model.form.UserForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface UserConverter {

    @Mapping(target = "organizations", ignore = true)
    @Mapping(target = "positions", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    UserLoginInfoDto entity2LoginInfoDto(UserEntity entity);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    UserInfoDto entity2InfoDto(UserEntity entity);

    @Mapping(target = "displayName", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "mobileCountryCode", ignore = true)
    @Mapping(target = "mobileNumber", ignore = true)
    @Mapping(target = "idCardType", ignore = true)
    @Mapping(target = "idCardNo", ignore = true)
    @Mapping(target = "sex", ignore = true)
    @Mapping(target = "birthday", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "passwordExpireAt", ignore = true)
    @Mapping(target = "passwordErrorAt", ignore = true)
    @Mapping(target = "passwordErrorCount", ignore = true)
    @Mapping(target = "lastLoginStatus", ignore = true)
    @Mapping(target = "lastLoginAt", ignore = true)
    UserEntity formToEntity(UserForm form);

}

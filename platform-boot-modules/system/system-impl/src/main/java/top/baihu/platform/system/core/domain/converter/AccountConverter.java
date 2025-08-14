package top.baihu.platform.system.core.domain.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import top.baihu.platform.system.core.domain.dto.AccountDto;
import top.baihu.platform.system.core.domain.dto.AccountInfoDto;
import top.baihu.platform.system.core.domain.dto.AccountLoginInfoDto;
import top.baihu.platform.system.core.domain.dto.UserInfoDto;
import top.baihu.platform.system.core.domain.entity.AccountEntity;
import top.baihu.platform.system.core.domain.form.AccountForm;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface AccountConverter {

    @Mapping(target = "organizations", ignore = true)
    @Mapping(target = "positions", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    AccountLoginInfoDto entity2LoginInfoDto(AccountEntity entity);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    AccountInfoDto entity2InfoDto(AccountEntity entity);

    @Mapping(target = "name", ignore = true)
    @Mapping(target = "mobileCountryCode", ignore = true)
    @Mapping(target = "mobileNumber", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    UserInfoDto entity2BaseUserInfoDto(AccountEntity entity);

    @Mapping(target = "name", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "mobileCountryCode", ignore = true)
    @Mapping(target = "mobileNumber", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    AccountDto entity2BaseUserInfoDtoNotHaveEmail(AccountEntity entity);

    @Mapping(target = "name", ignore = true)
    @Mapping(target = "mobileCountryCode", ignore = true)
    @Mapping(target = "mobileNumber", ignore = true)
    @Mapping(target = "idCardType", ignore = true)
    @Mapping(target = "idCardNo", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "passwordExpireAt", ignore = true)
    @Mapping(target = "passwordErrorAt", ignore = true)
    @Mapping(target = "passwordErrorCount", ignore = true)
    @Mapping(target = "lastLoginStatus", ignore = true)
    @Mapping(target = "lastLoginAt", ignore = true)
    AccountEntity formToEntity(AccountForm form);

}

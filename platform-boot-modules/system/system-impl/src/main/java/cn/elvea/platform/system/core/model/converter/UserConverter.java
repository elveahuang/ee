package cn.elvea.platform.system.core.model.converter;

import cn.elvea.platform.system.core.model.dto.UserInfoDto;
import cn.elvea.platform.system.core.model.dto.UserLoginDto;
import cn.elvea.platform.system.core.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 0.0.1
 */
@Mapper
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    @Mapping(target = "organizations", ignore = true)
    @Mapping(target = "positions", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    UserLoginDto entity2UserLoginDto(UserEntity entity);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    UserInfoDto entity2UserInfoDto(UserEntity entity);

}

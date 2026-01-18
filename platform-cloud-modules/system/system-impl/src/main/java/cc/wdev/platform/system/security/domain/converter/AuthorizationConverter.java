package cc.wdev.platform.system.security.domain.converter;

import cc.wdev.platform.system.security.domain.dto.AuthorizationDto;
import cc.wdev.platform.system.security.domain.entity.AuthorizationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface AuthorizationConverter {

    AuthorizationDto entity2Dto(AuthorizationEntity entity);

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deletedBy", ignore = true)
    AuthorizationEntity dto2Entity(AuthorizationDto dto);

}

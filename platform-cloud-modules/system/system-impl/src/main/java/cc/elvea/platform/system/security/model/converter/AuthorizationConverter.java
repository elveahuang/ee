package cc.elvea.platform.system.security.model.converter;

import cc.elvea.platform.system.security.model.dto.AuthorizationDto;
import cc.elvea.platform.system.security.model.entity.AuthorizationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface AuthorizationConverter {

    AuthorizationDto entity2Dto(AuthorizationEntity entity);

    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deletedBy", ignore = true)
    AuthorizationEntity dto2Entity(AuthorizationDto dto);

}

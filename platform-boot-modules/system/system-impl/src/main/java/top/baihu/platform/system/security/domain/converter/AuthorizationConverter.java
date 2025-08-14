package top.baihu.platform.system.security.domain.converter;

import org.mapstruct.Mapper;
import top.baihu.platform.system.security.domain.dto.AuthorizationDto;
import top.baihu.platform.system.security.domain.entity.AuthorizationEntity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface AuthorizationConverter {

    AuthorizationDto entity2Dto(AuthorizationEntity entity);

    //
//    @Mapping(target = "active", ignore = true)
//    @Mapping(target = "createdAt", ignore = true)
//    @Mapping(target = "createdBy", ignore = true)
//    @Mapping(target = "lastModifiedAt", ignore = true)
//    @Mapping(target = "lastModifiedBy", ignore = true)
//    @Mapping(target = "deletedAt", ignore = true)
//    @Mapping(target = "deletedBy", ignore = true)
//    @Mapping(target = "deletedBy", ignore = true)
    AuthorizationEntity dto2Entity(AuthorizationDto dto);

}

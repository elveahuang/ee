package cn.elvea.platform.system.security.model.converter;

import cn.elvea.platform.system.security.model.dto.AuthorizationDto;
import cn.elvea.platform.system.security.model.entity.AuthorizationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 0.0.1
 */
@Mapper
public interface AuthorizationConverter {

    AuthorizationConverter INSTANCE = Mappers.getMapper(AuthorizationConverter.class);

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

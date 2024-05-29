package cc.elvea.platform.system.core.model.converter;

import cc.elvea.platform.system.core.model.dto.UserSessionDto;
import cc.elvea.platform.system.core.model.entity.UserSessionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface UserSessionConverter {

    UserSessionConverter INSTANCE = Mappers.getMapper(UserSessionConverter.class);

    UserSessionDto entity2Dto(UserSessionEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deletedBy", ignore = true)
    UserSessionEntity dto2Entity(UserSessionDto dto);

}

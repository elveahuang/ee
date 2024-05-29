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

    @Mapping(target = "actionType", ignore = true)
    UserSessionDto entity2Dto(UserSessionEntity entity);

    UserSessionEntity dto2Entity(UserSessionDto dto);

}

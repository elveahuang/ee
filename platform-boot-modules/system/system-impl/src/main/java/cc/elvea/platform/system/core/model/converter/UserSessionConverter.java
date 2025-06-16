package cc.elvea.platform.system.core.model.converter;

import cc.elvea.platform.system.core.model.dto.UserSessionDto;
import cc.elvea.platform.system.core.model.entity.UserSessionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface UserSessionConverter {

    @Mapping(target = "actionType", ignore = true)
    UserSessionDto entity2Dto(UserSessionEntity entity);

    UserSessionEntity dto2Entity(UserSessionDto dto);

}

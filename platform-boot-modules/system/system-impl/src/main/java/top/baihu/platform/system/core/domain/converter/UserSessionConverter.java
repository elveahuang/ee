package top.baihu.platform.system.core.domain.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import top.baihu.platform.system.core.domain.dto.UserSessionDto;
import top.baihu.platform.system.core.domain.entity.UserSessionEntity;

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

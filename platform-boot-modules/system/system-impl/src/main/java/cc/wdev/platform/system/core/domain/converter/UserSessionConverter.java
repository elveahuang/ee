package cc.wdev.platform.system.core.domain.converter;

import cc.wdev.platform.system.core.domain.dto.LoginSessionDto;
import cc.wdev.platform.system.core.domain.entity.LoginSessionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface UserSessionConverter {

    @Mapping(target = "actionType", ignore = true)
    LoginSessionDto entity2Dto(LoginSessionEntity entity);

    LoginSessionEntity dto2Entity(LoginSessionDto dto);

}

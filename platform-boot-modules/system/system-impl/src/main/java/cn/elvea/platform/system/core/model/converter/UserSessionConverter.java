package cn.elvea.platform.system.core.model.converter;

import cn.elvea.platform.system.core.model.dto.UserSessionDto;
import cn.elvea.platform.system.core.model.entity.UserSessionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 0.0.1
 */
@Mapper
public interface UserSessionConverter {

    UserSessionConverter INSTANCE = Mappers.getMapper(UserSessionConverter.class);

    UserSessionDto entity2Dto(UserSessionEntity entity);

    UserSessionEntity dto2Entity(UserSessionDto dto);

}

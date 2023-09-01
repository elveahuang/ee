package cn.elvea.platform.system.core.model.converter;

import cn.elvea.platform.system.core.model.dto.ConfigDto;
import cn.elvea.platform.system.core.model.entity.ConfigEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 0.0.1
 */
@Mapper
public interface ConfigConverter {

    ConfigConverter INSTANCE = Mappers.getMapper(ConfigConverter.class);

    ConfigDto entity2Dto(ConfigEntity entity);

}

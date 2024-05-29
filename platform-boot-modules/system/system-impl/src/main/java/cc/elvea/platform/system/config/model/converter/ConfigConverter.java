package cc.elvea.platform.system.config.model.converter;

import cc.elvea.platform.system.config.model.dto.ConfigDto;
import cc.elvea.platform.system.config.model.entity.ConfigEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface ConfigConverter {

    ConfigConverter INSTANCE = Mappers.getMapper(ConfigConverter.class);

    ConfigDto entityToDto(ConfigEntity entity);

}

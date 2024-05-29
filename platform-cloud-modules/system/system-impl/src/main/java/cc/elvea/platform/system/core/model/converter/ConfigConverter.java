package cc.elvea.platform.system.core.model.converter;

import cc.elvea.platform.system.core.model.dto.ConfigDto;
import cc.elvea.platform.system.core.model.entity.ConfigEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface ConfigConverter {

    ConfigConverter INSTANCE = Mappers.getMapper(ConfigConverter.class);

    ConfigDto entity2Dto(ConfigEntity entity);

}

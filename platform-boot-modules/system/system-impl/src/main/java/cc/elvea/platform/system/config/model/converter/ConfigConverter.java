package cc.elvea.platform.system.config.model.converter;

import cc.elvea.platform.system.config.model.dto.ConfigDto;
import cc.elvea.platform.system.config.model.entity.ConfigEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface ConfigConverter {

    ConfigDto entityToDto(ConfigEntity entity);

}

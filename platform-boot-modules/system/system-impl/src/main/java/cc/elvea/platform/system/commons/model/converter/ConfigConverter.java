package cc.elvea.platform.system.commons.model.converter;

import cc.elvea.platform.system.commons.model.dto.ConfigDto;
import cc.elvea.platform.system.commons.model.entity.ConfigEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface ConfigConverter {

    ConfigDto entityToDto(ConfigEntity entity);

}

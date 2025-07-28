package cc.elvea.platform.system.core.domain.converter;

import cc.elvea.platform.system.core.domain.dto.ConfigDto;
import cc.elvea.platform.system.core.domain.entity.ConfigEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface ConfigConverter {

    ConfigDto entity2Dto(ConfigEntity entity);

}

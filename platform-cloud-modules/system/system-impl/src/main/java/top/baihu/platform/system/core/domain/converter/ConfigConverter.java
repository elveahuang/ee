package top.baihu.platform.system.core.domain.converter;

import org.mapstruct.Mapper;
import top.baihu.platform.system.core.domain.dto.ConfigDto;
import top.baihu.platform.system.core.domain.entity.ConfigEntity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface ConfigConverter {

    ConfigDto entity2Dto(ConfigEntity entity);

}

package top.baihu.platform.system.core.domain.converter;

import org.mapstruct.Mapper;
import top.baihu.platform.system.core.domain.dto.CatalogTypeDto;
import top.baihu.platform.system.core.domain.entity.CatalogTypeEntity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface CatalogTypeConverter {

    CatalogTypeDto entityToDto(CatalogTypeEntity entity);

}

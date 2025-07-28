package cc.elvea.platform.system.core.domain.converter;

import cc.elvea.platform.system.core.domain.dto.CatalogTypeDto;
import cc.elvea.platform.system.core.domain.entity.CatalogTypeEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface CatalogTypeConverter {

    CatalogTypeDto entityToDto(CatalogTypeEntity entity);

}

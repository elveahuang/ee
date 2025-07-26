package cc.elvea.platform.system.commons.model.converter;

import cc.elvea.platform.system.catalog.model.dto.CatalogTypeDto;
import cc.elvea.platform.system.commons.model.entity.CatalogTypeEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface CatalogTypeConverter {

    CatalogTypeDto entityToDto(CatalogTypeEntity entity);

}

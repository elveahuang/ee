package cc.elvea.platform.system.catalog.model.converter;

import cc.elvea.platform.system.catalog.model.dto.CatalogTypeDto;
import cc.elvea.platform.system.catalog.model.entity.CatalogTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 */
@Mapper
public interface CatalogTypeConverter {

    CatalogTypeConverter INSTANCE = Mappers.getMapper(CatalogTypeConverter.class);

    CatalogTypeDto entityToDto(CatalogTypeEntity entity);

}

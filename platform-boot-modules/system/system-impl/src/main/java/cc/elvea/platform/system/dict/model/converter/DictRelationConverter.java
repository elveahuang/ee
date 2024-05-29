package cc.elvea.platform.system.dict.model.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface DictRelationConverter {

    DictRelationConverter INSTANCE = Mappers.getMapper(DictRelationConverter.class);

}

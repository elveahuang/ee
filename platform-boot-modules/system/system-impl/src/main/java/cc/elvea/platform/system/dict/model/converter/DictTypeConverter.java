package cc.elvea.platform.system.dict.model.converter;

import cc.elvea.platform.system.dict.model.entity.DictTypeEntity;
import cc.elvea.platform.system.dict.model.vo.DictTypeVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface DictTypeConverter {

    DictTypeConverter INSTANCE = Mappers.getMapper(DictTypeConverter.class);

    @Mapping(target = "items", ignore = true)
    DictTypeVo entity2Vo(DictTypeEntity entity);

}

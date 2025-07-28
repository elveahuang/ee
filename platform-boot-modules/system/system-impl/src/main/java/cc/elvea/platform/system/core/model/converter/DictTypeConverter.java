package cc.elvea.platform.system.core.model.converter;

import cc.elvea.platform.system.core.model.entity.DictTypeEntity;
import cc.elvea.platform.system.core.model.vo.DictTypeVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface DictTypeConverter {

    @Mapping(target = "items", ignore = true)
    DictTypeVo entity2Vo(DictTypeEntity entity);

}

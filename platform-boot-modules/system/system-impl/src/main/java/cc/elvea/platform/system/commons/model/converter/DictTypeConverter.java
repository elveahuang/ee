package cc.elvea.platform.system.commons.model.converter;

import cc.elvea.platform.system.commons.model.entity.DictTypeEntity;
import cc.elvea.platform.system.commons.model.vo.DictTypeVo;
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

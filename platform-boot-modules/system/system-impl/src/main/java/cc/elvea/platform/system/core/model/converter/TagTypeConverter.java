package cc.elvea.platform.system.core.model.converter;

import cc.elvea.platform.system.core.model.entity.TagTypeEntity;
import cc.elvea.platform.system.core.model.vo.TagTypeVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface TagTypeConverter {

    @Mapping(target = "items", ignore = true)
    TagTypeVo entity2Vo(TagTypeEntity entity);

}

package cc.elvea.platform.system.tag.model.converter;

import cc.elvea.platform.system.tag.model.entity.TagTypeEntity;
import cc.elvea.platform.system.tag.model.vo.TagTypeVo;
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

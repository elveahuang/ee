package cc.elvea.platform.system.tag.model.converter;

import cc.elvea.platform.system.tag.model.entity.TagTypeEntity;
import cc.elvea.platform.system.tag.model.vo.TagTypeVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface TagTypeConverter {

    TagTypeConverter INSTANCE = Mappers.getMapper(TagTypeConverter.class);

    @Mapping(target = "items", ignore = true)
    TagTypeVo entity2Vo(TagTypeEntity entity);

}

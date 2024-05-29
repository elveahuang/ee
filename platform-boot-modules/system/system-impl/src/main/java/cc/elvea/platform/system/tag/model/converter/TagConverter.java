package cc.elvea.platform.system.tag.model.converter;

import cc.elvea.platform.system.tag.model.entity.TagEntity;
import cc.elvea.platform.system.tag.model.form.TagForm;
import cc.elvea.platform.system.tag.model.vo.TagVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface TagConverter {

    TagConverter INSTANCE = Mappers.getMapper(TagConverter.class);

    @Mapping(target = "idx", ignore = true)
    @Mapping(target = "source", ignore = true)
    TagEntity form2Entity(TagForm form);

    TagVo entity2Vo(TagEntity entity);

}

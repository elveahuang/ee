package cc.wdev.platform.system.core.domain.converter;

import cc.wdev.platform.system.core.domain.entity.TagEntity;
import cc.wdev.platform.system.core.domain.form.TagForm;
import cc.wdev.platform.system.core.domain.vo.TagVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface TagConverter {

    @Mapping(target = "idx", ignore = true)
    @Mapping(target = "source", ignore = true)
    TagEntity form2Entity(TagForm form);

    TagVo entity2Vo(TagEntity entity);

}

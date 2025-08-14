package top.baihu.platform.system.core.domain.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import top.baihu.platform.system.core.domain.entity.TagEntity;
import top.baihu.platform.system.core.domain.form.TagForm;
import top.baihu.platform.system.core.domain.vo.TagVo;

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

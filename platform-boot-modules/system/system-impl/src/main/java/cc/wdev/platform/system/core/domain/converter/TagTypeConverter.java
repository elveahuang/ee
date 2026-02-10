package cc.wdev.platform.system.core.domain.converter;

import cc.wdev.platform.system.core.domain.entity.TagTypeEntity;
import cc.wdev.platform.system.core.domain.vo.TagTypeVo;
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

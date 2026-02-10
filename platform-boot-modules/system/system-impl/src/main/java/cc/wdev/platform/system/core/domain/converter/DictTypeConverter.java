package cc.wdev.platform.system.core.domain.converter;

import cc.wdev.platform.system.core.domain.entity.DictTypeEntity;
import cc.wdev.platform.system.core.domain.vo.DictTypeVo;
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

package cc.wdev.platform.system.core.domain.converter;

import cc.wdev.platform.system.core.domain.entity.DictItemEntity;
import cc.wdev.platform.system.core.domain.form.DictForm;
import cc.wdev.platform.system.core.domain.vo.DictItemVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface DictItemConverter {

    @Mapping(target = "idx", ignore = true)
    @Mapping(target = "source", ignore = true)
    DictItemEntity form2Entity(DictForm form);

    DictItemVo entity2Vo(DictItemEntity entity);

}

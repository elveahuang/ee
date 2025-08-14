package top.baihu.platform.system.core.domain.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import top.baihu.platform.system.core.domain.entity.DictItemEntity;
import top.baihu.platform.system.core.domain.form.DictForm;
import top.baihu.platform.system.core.domain.vo.DictItemVo;

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

package cc.elvea.platform.system.dict.model.converter;

import cc.elvea.platform.system.dict.model.entity.DictItemEntity;
import cc.elvea.platform.system.dict.model.form.DictForm;
import cc.elvea.platform.system.dict.model.vo.DictItemVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface DictItemConverter {

    DictItemConverter INSTANCE = Mappers.getMapper(DictItemConverter.class);

    @Mapping(target = "idx", ignore = true)
    @Mapping(target = "source", ignore = true)
    DictItemEntity form2Entity(DictForm form);

    DictItemVo entity2Vo(DictItemEntity entity);

}

package cc.elvea.platform.system.mall.model.converter;

import cc.elvea.platform.system.mall.model.entity.VipTypeEntity;
import cc.elvea.platform.system.mall.model.vo.VipTypeVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface VipTypeConverter {

    VipTypeConverter INSTANCE = Mappers.getMapper(VipTypeConverter.class);

    @Mapping(target = "items", ignore = true)
    @Mapping(target = "accountVip", ignore = true)
    VipTypeVo entity2Vo(VipTypeEntity entity);

}

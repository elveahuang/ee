package cc.elvea.platform.system.mall.domain.converter;

import cc.elvea.platform.system.mall.domain.entity.VipTypeEntity;
import cc.elvea.platform.system.mall.domain.vo.VipTypeVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface VipTypeConverter {

    @Mapping(target = "items", ignore = true)
    @Mapping(target = "accountVip", ignore = true)
    VipTypeVo entity2Vo(VipTypeEntity entity);

}

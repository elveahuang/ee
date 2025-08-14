package top.baihu.platform.system.mall.domain.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import top.baihu.platform.system.mall.domain.entity.VipTypeEntity;
import top.baihu.platform.system.mall.domain.vo.VipTypeVo;

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

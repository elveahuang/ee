package top.baihu.platform.system.mall.domain.converter;

import org.mapstruct.Mapper;
import top.baihu.platform.system.mall.domain.entity.VipItemEntity;
import top.baihu.platform.system.mall.domain.vo.VipItemVo;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface VipItemConverter {

    VipItemVo entity2Vo(VipItemEntity entity);

}

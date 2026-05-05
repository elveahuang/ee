package cc.wdev.platform.system.mall.domain.converter;

import cc.wdev.platform.system.mall.domain.entity.VipItemEntity;
import cc.wdev.platform.system.mall.domain.vo.VipItemVo;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface VipItemConverter {

    VipItemVo entity2Vo(VipItemEntity entity);

}

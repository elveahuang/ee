package cc.elvea.platform.system.mall.model.converter;

import cc.elvea.platform.system.mall.model.entity.VipItemEntity;
import cc.elvea.platform.system.mall.model.vo.VipItemVo;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface VipItemConverter {

    VipItemVo entity2Vo(VipItemEntity entity);

}

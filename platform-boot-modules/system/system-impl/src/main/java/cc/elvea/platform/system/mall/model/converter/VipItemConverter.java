package cc.elvea.platform.system.mall.model.converter;

import cc.elvea.platform.system.mall.model.entity.VipItemEntity;
import cc.elvea.platform.system.mall.model.vo.VipItemVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface VipItemConverter {

    VipItemConverter INSTANCE = Mappers.getMapper(VipItemConverter.class);

    VipItemVo entity2Vo(VipItemEntity entity);

}

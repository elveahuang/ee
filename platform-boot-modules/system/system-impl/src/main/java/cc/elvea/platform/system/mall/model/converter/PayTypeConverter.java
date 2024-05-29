package cc.elvea.platform.system.mall.model.converter;

import cc.elvea.platform.system.mall.model.entity.PayTypeEntity;
import cc.elvea.platform.system.mall.model.vo.PayTypeVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface PayTypeConverter {

    PayTypeConverter INSTANCE = Mappers.getMapper(PayTypeConverter.class);

    List<PayTypeVo> entityList2VoList(List<PayTypeEntity> entityList);

}

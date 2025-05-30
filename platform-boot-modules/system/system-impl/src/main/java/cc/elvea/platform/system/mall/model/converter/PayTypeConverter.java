package cc.elvea.platform.system.mall.model.converter;

import cc.elvea.platform.system.mall.model.entity.PayTypeEntity;
import cc.elvea.platform.system.mall.model.vo.PayTypeVo;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface PayTypeConverter {

    List<PayTypeVo> entityList2VoList(List<PayTypeEntity> entityList);

}

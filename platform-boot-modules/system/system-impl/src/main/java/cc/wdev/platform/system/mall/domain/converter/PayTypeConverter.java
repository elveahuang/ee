package cc.wdev.platform.system.mall.domain.converter;

import cc.wdev.platform.system.mall.domain.entity.PayTypeEntity;
import cc.wdev.platform.system.mall.domain.vo.PayTypeVo;
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

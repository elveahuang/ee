package top.baihu.platform.system.mall.domain.converter;

import org.mapstruct.Mapper;
import top.baihu.platform.system.mall.domain.entity.PayTypeEntity;
import top.baihu.platform.system.mall.domain.vo.PayTypeVo;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface PayTypeConverter {

    List<PayTypeVo> entityList2VoList(List<PayTypeEntity> entityList);

}

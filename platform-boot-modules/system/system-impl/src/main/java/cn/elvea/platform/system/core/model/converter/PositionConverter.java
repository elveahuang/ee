package cn.elvea.platform.system.core.model.converter;

import cn.elvea.platform.system.core.model.dto.PositionDto;
import cn.elvea.platform.system.core.model.dto.PositionSaveDto;
import cn.elvea.platform.system.core.model.entity.PositionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 0.0.1
 */
@Mapper
public interface PositionConverter {

    PositionConverter INSTANCE = Mappers.getMapper(PositionConverter.class);

    @Mapping(target = "rootInd", ignore = true)
    @Mapping(target = "defaultInd", ignore = true)
    PositionEntity saveDto2Entity(PositionSaveDto saveDto);

    PositionDto entity2Dto(PositionEntity entity);

}

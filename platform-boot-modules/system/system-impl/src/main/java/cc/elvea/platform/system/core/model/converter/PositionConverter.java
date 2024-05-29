package cc.elvea.platform.system.core.model.converter;

import cc.elvea.platform.system.core.model.dto.PositionDto;
import cc.elvea.platform.system.core.model.dto.PositionSaveDto;
import cc.elvea.platform.system.core.model.entity.PositionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface PositionConverter {

    PositionConverter INSTANCE = Mappers.getMapper(PositionConverter.class);

    @Mapping(target = "rootInd", ignore = true)
    @Mapping(target = "defaultInd", ignore = true)
    PositionEntity saveDto2Entity(PositionSaveDto saveDto);

    PositionDto entity2Dto(PositionEntity entity);

}

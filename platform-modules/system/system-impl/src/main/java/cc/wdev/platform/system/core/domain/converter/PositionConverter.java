package cc.wdev.platform.system.core.domain.converter;

import cc.wdev.platform.system.core.domain.dto.PositionDto;
import cc.wdev.platform.system.core.domain.dto.PositionSaveDto;
import cc.wdev.platform.system.core.domain.entity.PositionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface PositionConverter {

    @Mapping(target = "rootInd", ignore = true)
    @Mapping(target = "defaultInd", ignore = true)
    PositionEntity saveDto2Entity(PositionSaveDto saveDto);

    PositionDto entity2Dto(PositionEntity entity);

}

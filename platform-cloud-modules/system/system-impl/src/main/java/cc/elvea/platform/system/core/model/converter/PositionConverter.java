package cc.elvea.platform.system.core.model.converter;

import cc.elvea.platform.system.core.model.dto.PositionDto;
import cc.elvea.platform.system.core.model.dto.PositionSaveDto;
import cc.elvea.platform.system.core.model.entity.PositionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface PositionConverter {

    List<PositionDto> entityListToDtoList(List<PositionEntity> entityList);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deletedBy", ignore = true)
    PositionEntity saveDtoToEntity(PositionSaveDto saveDto);

    PositionDto entityToDto(PositionEntity entity);

}

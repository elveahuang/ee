package cc.elvea.platform.system.core.model.converter;

import cc.elvea.platform.system.core.model.dto.PositionDto;
import cc.elvea.platform.system.core.model.dto.PositionSaveDto;
import cc.elvea.platform.system.core.model.entity.PositionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface PositionConverter {

    PositionConverter INSTANCE = Mappers.getMapper(PositionConverter.class);

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

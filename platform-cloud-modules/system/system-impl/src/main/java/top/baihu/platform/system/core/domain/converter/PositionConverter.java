package top.baihu.platform.system.core.domain.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import top.baihu.platform.system.core.domain.dto.PositionDto;
import top.baihu.platform.system.core.domain.dto.PositionSaveDto;
import top.baihu.platform.system.core.domain.entity.PositionEntity;

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

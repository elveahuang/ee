package cc.elvea.platform.system.core.model.converter;

import cc.elvea.platform.system.core.model.dto.OrganizationDto;
import cc.elvea.platform.system.core.model.dto.OrganizationSaveDto;
import cc.elvea.platform.system.core.model.entity.OrganizationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface OrganizationConverter {

    List<OrganizationDto> entityListToDtoList(List<OrganizationEntity> entityList);

    @Mapping(target = "defaultInd", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deletedBy", ignore = true)
    OrganizationEntity saveDtoToEntity(OrganizationSaveDto saveDto);

    OrganizationDto entityToDto(OrganizationEntity entity);

}

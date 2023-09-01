package cn.elvea.platform.system.core.model.converter;

import cn.elvea.platform.system.core.model.dto.OrganizationDto;
import cn.elvea.platform.system.core.model.dto.OrganizationSaveDto;
import cn.elvea.platform.system.core.model.entity.OrganizationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
 */
@Mapper
public interface OrganizationConverter {

    OrganizationConverter INSTANCE = Mappers.getMapper(OrganizationConverter.class);

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

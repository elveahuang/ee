package cc.elvea.platform.system.core.model.converter;

import cc.elvea.platform.system.core.model.dto.OrganizationDto;
import cc.elvea.platform.system.core.model.dto.OrganizationSaveDto;
import cc.elvea.platform.system.core.model.entity.OrganizationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface OrganizationConverter {

    @Mapping(target = "rootInd", ignore = true)
    @Mapping(target = "defaultInd", ignore = true)
    OrganizationEntity saveDto2Entity(OrganizationSaveDto saveDto);

    OrganizationDto entity2Dto(OrganizationEntity entity);

}

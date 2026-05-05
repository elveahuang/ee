package cc.wdev.platform.system.core.domain.converter;

import cc.wdev.platform.system.core.domain.dto.OrganizationDto;
import cc.wdev.platform.system.core.domain.dto.OrganizationSaveDto;
import cc.wdev.platform.system.core.domain.entity.OrganizationEntity;
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

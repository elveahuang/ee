package cc.elvea.platform.system.core.model.converter;

import cc.elvea.platform.system.core.model.dto.OrganizationDto;
import cc.elvea.platform.system.core.model.dto.OrganizationSaveDto;
import cc.elvea.platform.system.core.model.entity.OrganizationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface OrganizationConverter {

    OrganizationConverter INSTANCE = Mappers.getMapper(OrganizationConverter.class);

    @Mapping(target = "rootInd", ignore = true)
    @Mapping(target = "defaultInd", ignore = true)
    OrganizationEntity saveDto2Entity(OrganizationSaveDto saveDto);

    OrganizationDto entity2Dto(OrganizationEntity entity);

}

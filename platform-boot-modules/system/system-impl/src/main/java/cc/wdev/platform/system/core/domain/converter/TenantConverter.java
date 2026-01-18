package cc.wdev.platform.system.core.domain.converter;

import cc.wdev.platform.commons.core.tenant.Tenant;
import cc.wdev.platform.system.core.domain.dto.TenantDto;
import cc.wdev.platform.system.core.domain.entity.TenantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface TenantConverter {

    TenantConverter INSTANCE = Mappers.getMapper(TenantConverter.class);

    /**
     * 实体转DTO
     */
    TenantDto entity2Dto(TenantEntity entity);

    /**
     * DTO转核心租户对象
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "rootInd", ignore = true)
    Tenant dto2Tenant(TenantDto dto);

    /**
     * 实体转核心租户对象
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "rootInd", ignore = true)
    Tenant entity2Tenant(TenantEntity entity);

}

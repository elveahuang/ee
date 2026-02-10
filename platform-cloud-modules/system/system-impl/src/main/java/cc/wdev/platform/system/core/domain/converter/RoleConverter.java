package cc.wdev.platform.system.core.domain.converter;

import cc.wdev.platform.system.core.domain.dto.RoleDto;
import cc.wdev.platform.system.core.domain.entity.RoleEntity;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface RoleConverter {

    List<RoleDto> entityListToDtoList(List<RoleEntity> entityList);

}

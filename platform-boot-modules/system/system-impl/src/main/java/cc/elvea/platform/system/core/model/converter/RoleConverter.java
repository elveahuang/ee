package cc.elvea.platform.system.core.model.converter;

import cc.elvea.platform.system.core.model.dto.RoleDto;
import cc.elvea.platform.system.core.model.entity.RoleEntity;
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

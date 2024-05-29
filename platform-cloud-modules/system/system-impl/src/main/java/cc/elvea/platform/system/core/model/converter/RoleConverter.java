package cc.elvea.platform.system.core.model.converter;

import cc.elvea.platform.system.core.model.dto.RoleDto;
import cc.elvea.platform.system.core.model.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface RoleConverter {

    RoleConverter INSTANCE = Mappers.getMapper(RoleConverter.class);

    List<RoleDto> entityListToDtoList(List<RoleEntity> entityList);

}

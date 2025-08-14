package top.baihu.platform.system.core.domain.converter;

import org.mapstruct.Mapper;
import top.baihu.platform.system.core.domain.dto.RoleDto;
import top.baihu.platform.system.core.domain.entity.RoleEntity;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface RoleConverter {

    List<RoleDto> entityListToDtoList(List<RoleEntity> entityList);

}

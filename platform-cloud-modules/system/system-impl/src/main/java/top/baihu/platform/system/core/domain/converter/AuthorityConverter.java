package top.baihu.platform.system.core.domain.converter;

import org.mapstruct.Mapper;
import top.baihu.platform.system.core.domain.dto.AuthorityDto;
import top.baihu.platform.system.core.domain.entity.AuthorityEntity;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface AuthorityConverter {

    List<AuthorityDto> entityListToDtoList(List<AuthorityEntity> entityList);

}

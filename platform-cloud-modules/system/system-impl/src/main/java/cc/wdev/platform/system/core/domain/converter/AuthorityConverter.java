package cc.wdev.platform.system.core.domain.converter;

import cc.wdev.platform.system.core.domain.dto.AuthorityDto;
import cc.wdev.platform.system.core.domain.entity.AuthorityEntity;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface AuthorityConverter {

    List<AuthorityDto> entityListToDtoList(List<AuthorityEntity> entityList);

}

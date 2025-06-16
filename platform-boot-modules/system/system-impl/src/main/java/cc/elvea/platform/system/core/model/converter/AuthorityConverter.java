package cc.elvea.platform.system.core.model.converter;

import cc.elvea.platform.system.core.model.dto.AuthorityDto;
import cc.elvea.platform.system.core.model.entity.AuthorityEntity;
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

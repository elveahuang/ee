package cc.elvea.platform.system.core.model.converter;

import cc.elvea.platform.system.core.model.dto.AuthorityDto;
import cc.elvea.platform.system.core.model.entity.AuthorityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface AuthorityConverter {

    AuthorityConverter INSTANCE = Mappers.getMapper(AuthorityConverter.class);

    List<AuthorityDto> entityListToDtoList(List<AuthorityEntity> entityList);

}

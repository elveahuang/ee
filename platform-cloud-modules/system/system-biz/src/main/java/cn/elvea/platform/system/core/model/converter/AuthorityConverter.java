package cn.elvea.platform.system.core.model.converter;

import cn.elvea.platform.system.core.model.dto.AuthorityDto;
import cn.elvea.platform.system.core.model.entity.AuthorityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
 */
@Mapper
public interface AuthorityConverter {

    AuthorityConverter INSTANCE = Mappers.getMapper(AuthorityConverter.class);

    List<AuthorityDto> entityListToDtoList(List<AuthorityEntity> entityList);

}

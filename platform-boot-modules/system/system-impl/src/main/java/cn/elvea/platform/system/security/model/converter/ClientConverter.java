package cn.elvea.platform.system.security.model.converter;

import cn.elvea.platform.system.security.model.dto.ClientDto;
import cn.elvea.platform.system.security.model.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 0.0.1
 */
@Mapper
public interface ClientConverter {

    ClientConverter INSTANCE = Mappers.getMapper(ClientConverter.class);

    ClientDto entity2Dto(ClientEntity entity);

}

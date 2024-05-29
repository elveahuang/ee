package cc.elvea.platform.system.security.model.converter;

import cc.elvea.platform.system.security.model.dto.ClientDto;
import cc.elvea.platform.system.security.model.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface ClientConverter {

    ClientConverter INSTANCE = Mappers.getMapper(ClientConverter.class);

    ClientDto entity2Dto(ClientEntity entity);

}

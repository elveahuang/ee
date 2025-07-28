package cc.elvea.platform.system.security.domain.converter;

import cc.elvea.platform.system.security.domain.dto.ClientDto;
import cc.elvea.platform.system.security.domain.entity.ClientEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface ClientConverter {

    ClientDto entity2Dto(ClientEntity entity);

}

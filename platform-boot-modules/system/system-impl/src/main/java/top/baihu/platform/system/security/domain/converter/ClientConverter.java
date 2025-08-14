package top.baihu.platform.system.security.domain.converter;

import org.mapstruct.Mapper;
import top.baihu.platform.system.security.domain.dto.ClientDto;
import top.baihu.platform.system.security.domain.entity.ClientEntity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface ClientConverter {

    ClientDto entity2Dto(ClientEntity entity);

}

package cc.wdev.platform.system.core.domain.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface AuthorizationConverter {

    AuthorizationConverter INSTANCE = Mappers.getMapper(AuthorizationConverter.class);

}
